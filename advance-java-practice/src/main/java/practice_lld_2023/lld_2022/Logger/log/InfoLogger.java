package practice_lld_2023.lld_2022.Logger.log;


import practice_lld_2023.lld_2022.Logger.observers.LogSubject;

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
