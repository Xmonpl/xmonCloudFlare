package cf.xmon.cloudflare.config;

import cf.xmon.cloudflare.utils.json.JsonUtil;
import cf.xmon.cloudflare.utils.logger.LoggerUtils;

import java.io.File;

public class ConfigManager
{
    private static Config botConfig;

    public ConfigManager() {
        File f = new File("config.json");
        try {
            botConfig = JsonUtil.readConfiguration(Config.class, f);
        } catch (Exception ex) {
            LoggerUtils.warn(ex.getMessage());
            botConfig = null;
        }
    }

    public static Config getConfig() {
        return ConfigManager.botConfig;
    }
}
