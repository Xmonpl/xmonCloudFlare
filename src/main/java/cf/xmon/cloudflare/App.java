package cf.xmon.cloudflare;

import cf.xmon.cloudflare.config.Config;
import cf.xmon.cloudflare.config.ConfigManager;
import cf.xmon.cloudflare.task.CloudFlareTask;
import cf.xmon.cloudflare.utils.logger.LoggerUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {
    public static void main(String... args) throws IOException {
        new ConfigManager();
        Config c = ConfigManager.getConfig();
        if (args.length >= 1){
            if (args[0].equalsIgnoreCase("-check")) {
                LoggerUtils.info("xmonCloudFlare by Xmon https://github.com/xmonpl");
                String command = "curl -X GET \"https://api.cloudflare.com/client/v4/zones/" + c.getInstance().getZONE_ID() + "/dns_records" +
                        "\" -H \"X-Auth-Email:" + c.getInstance().getAPI_ID() + "\"" +
                        " -H \"Authorization: Bearer " + c.getInstance().getAPI_AUTH_KEY() + "\"" +
                        " -H \"Content-Type: application/json\"";
                System.out.println("\n\n\n");
                Process process = Runtime.getRuntime().exec(command);
                BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                while ((line = in.readLine()) != null) {
                    System.out.println(line);
                }
                System.out.println("\n\n\n");
                process.destroy();
                LoggerUtils.info("https://jsonformatter.org/json-pretty-print <-> Copy the entire JSON that is displayed and copy the 'id' of the subdomain that interests you.");
            }else{
                LoggerUtils.info("xmonCloudFlare by Xmon https://github.com/xmonpl\njava -jar xmonCloudFlare.jar (-check optional)");
                System.exit(-1);
            }
        }else {
            LoggerUtils.info("xmonCloudFlare by Xmon https://github.com/xmonpl");
            new CloudFlareTask();
        }
    }
}
