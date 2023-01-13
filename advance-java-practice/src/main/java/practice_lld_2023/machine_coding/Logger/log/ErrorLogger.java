package practice_lld_2023.machine_coding.Logger.log;

import practice_lld_2023.machine_coding.Logger.observers.LogSubject;

public class ErrorLogger extends AbstractLogger {

    public ErrorLogger(int level) {
        this.level = level;
    }

    @Override
    public void display(String message, LogSubject logSubject) {
        logSubject.notifyAll(LogType.ERROR, "ERROR : " + message);
    }
}
