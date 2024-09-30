package practice_lld_2023.machine_coding.Logger;

import practice_lld_2023.machine_coding.Logger.log.AbstractLogger;
import practice_lld_2023.machine_coding.Logger.log.LogType;
import practice_lld_2023.machine_coding.Logger.observers.LogSubject;

import java.io.Serializable;

public class Logger implements Cloneable, Serializable {
    private volatile static Logger logger;
    private volatile static AbstractLogger chainLogger;
    private volatile static LogSubject logSubject;

    public static Logger getLogger() {
        if (logger == null) {
            logger = new Logger();
            chainLogger = LoggerManager.buildLogger();
            logSubject = LoggerManager.buildLogSubject();
        }
        return logger;
    }

    public void info(String message) {
        levelLog(LogType.INFO.getLevel(), message);
    }

    public void error(String message) {
        levelLog(LogType.ERROR.getLevel(), message);
    }

    public void debug(String message) {
        levelLog(LogType.DEBUG.getLevel(), message);
    }

    private void levelLog(int level, String message) {
        chainLogger.log(level, message, logSubject);
    }
}
