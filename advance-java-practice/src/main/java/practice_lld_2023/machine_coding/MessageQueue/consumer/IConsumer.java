package practice_lld_2023.machine_coding.MessageQueue.consumer;

public interface IConsumer {

    void consume(String message, String topic);
    void ack(Integer consumerId, String topic);
}
