package practice_lld_2023.lld_2022.Logger.observers;

public class FileObserver implements LogObserver {

    @Override
    public void log(String message) {
        System.out.println("Logging in File " + message);
    }
}
