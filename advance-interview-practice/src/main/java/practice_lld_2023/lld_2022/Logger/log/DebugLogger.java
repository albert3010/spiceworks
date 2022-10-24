package practice_lld_2023.lld_2022.Logger.log;

import practice_lld_2023.lld_2022.Logger.observers.LogSubject;

public class DebugLogger extends AbstractLogger {

    public DebugLogger(int level) {
        this.level = level;
    }

    @Override
    public void display(String message, LogSubject logSubject) {
        logSubject.notifyAll(LogType.DEBUG, "Debug : " + message);
    }
}
