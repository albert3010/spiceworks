package practice_lld_2023.machine_coding.Logger.observers;

public class DatabaseObserver implements LogObserver {

    @Override
    public void log(String message) {
        System.out.println("Logging in Database " + message);
    }
}
