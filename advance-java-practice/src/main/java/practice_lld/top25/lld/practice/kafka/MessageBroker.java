package practice_lld.top25.lld.practice.kafka;

import lombok.Getter;
import practice_lld.top25.lld.practice.kafka.consumer.entity.OffsetStore;
import practice_lld.top25.lld.practice.kafka.entity.Message;
import practice_lld.top25.lld.practice.kafka.entity.Partition;
import practice_lld.top25.lld.practice.kafka.entity.Topic;
import practice_lld.top25.lld.practice.kafka.producer.entity.TopicRegistry;

@Getter
public class MessageBroker<K, V> {
    static MessageBroker instance;
    TopicRegistry topicRegistry;
    OffsetStore offsetStore;

    private MessageBroker(KafkaConfig kafkaConfig) {
        this.topicRegistry = kafkaConfig.getTopicRegistry();
        this.offsetStore = kafkaConfig.getOffsetStore();
    }

    public static MessageBroker getInstance(KafkaConfig kafkaConfig) {
        if (instance == null) {
            return new MessageBroker(kafkaConfig);
        }
        return instance;
    }

    public void publishMessage(String topicName, Message<K, V> message, int partitionId) {
        Topic<K, V> topic = topicRegistry.getTopic(topicName);
        Partition<K, V> partition = topic.getPartitions().get(partitionId);
        partition.addMessage(message);
    }

    public Message<K, V> consumeMessage(String topicName, int partitionId, int offset) {
        Topic<K, V> topic = topicRegistry.getTopic(topicName);
        Partition<K, V> partition = topic.getPartitions().get(partitionId);
        return partition.consume(offset);
    }
}
