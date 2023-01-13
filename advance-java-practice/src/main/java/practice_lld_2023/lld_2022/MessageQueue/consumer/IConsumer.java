package practice_lld_2023.lld_2022.MessageQueue.consumer;

public interface IConsumer {

    void consume(String message, String topic);
    void ack(Integer consumerId, String topic);
}
