

public class Consumer implements Observer{
    private String consumerId;

    public Consumer(String consumerId) {
        this.consumerId = consumerId;
    }

    public String getConsumerId() {
        return consumerId;
    }

    @Override
    public void processEvents(String message) {
        System.out.println("Consumer : " + consumerId + " , message " + message);
    }
}
