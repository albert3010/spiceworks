package practice_lld_2023.machine_coding.Logger.observers;

public class FileObserver implements LogObserver {

    @Override
    public void log(String message) {
        System.out.println("Logging in File " + message);
    }
}
