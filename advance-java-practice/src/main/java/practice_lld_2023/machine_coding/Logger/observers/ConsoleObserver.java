package practice_lld_2023.machine_coding.Logger.observers;

public class ConsoleObserver implements LogObserver {

    @Override
    public void log(String message) {
        System.out.println("Logging in Console " + message);
    }
}
