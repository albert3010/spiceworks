package practice_lld_2023.machine_coding.Logger.log;

import practice_lld_2023.machine_coding.Logger.observers.LogSubject;

public abstract class AbstractLogger {
    int level;
    AbstractLogger nextLogger;

    public void setNextLogger(AbstractLogger nextLogger) {
        this.nextLogger = nextLogger;
    }

    public void log(int level, String message, LogSubject logSubject) {
        if (this.level <= level) {
            display(message, logSubject);
        }
        if (nextLogger != null) {
            nextLogger.log(level, message, logSubject);
        }

    }

    public abstract void display(String message, LogSubject logSubject);
}
