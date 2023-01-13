package practice_lld_2023.machine_coding.Logger.log;


import practice_lld_2023.machine_coding.Logger.observers.LogSubject;

public class InfoLogger extends AbstractLogger {

    public InfoLogger(int level) {
        this.level = level;
    }

    @Override
    public void display(String message, LogSubject logSubject) {
//        System.out.println("INFO : " + message);
        logSubject.notifyAll(LogType.INFO, "INFO : " + message);
    }
}
