package practice_lld.top25.lld.kafka.producer;

import lombok.AllArgsConstructor;
import practice_lld.top25.lld.kafka.MessageBroker;
import practice_lld.top25.lld.kafka.entity.Message;
import practice_lld.top25.lld.kafka.producer.entity.Partitioner;
import practice_lld.top25.lld.kafka.producer.entity.Producer;

@AllArgsConstructor
public class KafkaProducer<K,V > implements Producer<K, V> {
    private MessageBroker messageBroker;
    private Partitioner partitioner;

    @Override
    public void sendMessage(String topicName, Message<K, V> message) {
        int partitionId = partitioner.partition(topicName, message.key(), message.value());
        messageBroker.publishMessage(topicName, message, partitionId);
    }
}
