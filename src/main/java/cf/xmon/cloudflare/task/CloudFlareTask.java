package cf.xmon.cloudflare.task;

import cf.xmon.cloudflare.config.Config;
import cf.xmon.cloudflare.config.ConfigManager;
import cf.xmon.cloudflare.utils.json.JsonUtil;
import cf.xmon.cloudflare.utils.logger.LoggerUtils;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class CloudFlareTask {
    private final ResponseReader responseReader = new ResponseReader();
    public CloudFlareTask(){
        this.update();
    }
    private void update() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                final String responseip;
                try{
                    responseip = responseReader.read("https://ipecho.net/plain");
                    LoggerUtils.info("Public ip " + responseip);
                    new ConfigManager();
                    Config c = ConfigManager.getConfig();
                    if (!c.getInstance().getIP().equals(responseip)){
                        LoggerUtils.info("Change ip from " + c.getInstance().getIP() + " to " + responseip);
                        c.getInstance().setIP(responseip);
                        JsonUtil.save();
                        final String command = "curl -s -X PUT \"https://api.cloudflare.com/client/v4/zones/" + c.getInstance().getZONE_ID() + "/dns_records/" + c.getInstance().getDNSREC_ID() + "\" -H \"X-Auth-Email: " + c.getInstance().getAPI_ID() + "\" -H \"Authorization: Bearer " + c.getInstance().getAPI_AUTH_KEY() + "\" -H \"Content-Type: application/json\" --data '{\"type\":\"A\",\"name\":\"" + c.getInstance().getZONE_NAME() + "\",\"content\":\"" + c.getInstance().getIP() + "\",\"ttl\":1,\"proxied\":false}";
                        final Process process = Runtime.getRuntime().exec(command);
                        final BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
                        String line;
                        while ((line = in.readLine()) != null) {
                            System.out.println(line);
                        }
                        process.destroy();
                    }else{
                        LoggerUtils.info("IP unchanged");
                    }
                }catch (IOException e){
                    LoggerUtils.warn(e.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, TimeUnit.SECONDS.toMillis(30), TimeUnit.SECONDS.toMillis(30));
    }
    static class ResponseReader {
        String read(String urll) throws IOException {
            final URL url = new URL(urll);
            final HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            return new BufferedReader(new InputStreamReader(con.getInputStream())).readLine();
        }
    }
}
