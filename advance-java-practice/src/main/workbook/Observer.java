public interface Observer {

    String getConsumerId();

    void processEvents(String message);
}
