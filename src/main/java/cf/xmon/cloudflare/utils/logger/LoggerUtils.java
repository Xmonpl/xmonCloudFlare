package cf.xmon.cloudflare.utils.logger;

import com.sun.istack.internal.logging.Logger;

public final class LoggerUtils {
    private static final Logger logger = Logger.getLogger(LoggerUtils.class);

    public static void info(String s){
        logger.info(s);
    }

    public static void warn(String s){
        logger.warning(s);
    }
}
