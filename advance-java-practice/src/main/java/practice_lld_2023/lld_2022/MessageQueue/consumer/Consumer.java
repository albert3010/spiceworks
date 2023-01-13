package practice_lld_2023.lld_2022.MessageQueue.consumer;

import practice_lld_2023.lld_2022.MessageQueue.QueueService;


public class Consumer implements IConsumer {
    private Integer consumerId;
    private QueueService queueService;

    public Consumer(Integer consumerId, QueueService queueService) {
        this.consumerId = consumerId;
        this.queueService = queueService;
    }

    public Integer getConsumerId() {
        return consumerId;
    }

    @Override
    public void consume(String message, String topic) {
        System.out.println("Consumer "+ consumerId + " received message " + message);
        ack(consumerId, topic);
    }

    public void ack(Integer consumerId, String topic) {
        queueService.consumerAck(consumerId, topic);
    }


}
