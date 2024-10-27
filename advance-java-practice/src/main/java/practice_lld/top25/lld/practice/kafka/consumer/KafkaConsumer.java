package practice_lld.top25.lld.practice.kafka.consumer;

import practice_lld.top25.lld.practice.kafka.MessageBroker;
import practice_lld.top25.lld.practice.kafka.consumer.entity.Consumer;
import practice_lld.top25.lld.practice.kafka.consumer.entity.ConsumerRecords;
import practice_lld.top25.lld.practice.kafka.consumer.entity.OffsetStore;
import practice_lld.top25.lld.practice.kafka.entity.Message;
import practice_lld.top25.lld.practice.kafka.entity.Topic;

import java.util.List;
import java.util.UUID;

public class KafkaConsumer<K, V> implements Consumer<K, V> {
    private String consumerId;
    private Topic topic;
    int partitionId;
    OffsetStore offsetStore;
    MessageBroker messageBroker;
    int defaultLimit = 1;

    public KafkaConsumer(Topic topic, int partitionId, MessageBroker messageBroker) {
        this.consumerId = UUID.randomUUID().toString();
        this.topic = topic;
        this.partitionId = partitionId;
        this.offsetStore = messageBroker.getOffsetStore();
        this.messageBroker = messageBroker;
    }

    public ConsumerRecords<K, V> consumeMessage() {
        ConsumerRecords records = new ConsumerRecords(topic.getTopicName());
        int offset = offsetStore.getOffset(topic.getTopicName(), consumerId, partitionId);
        Message<K, V> message = messageBroker.consumeMessage(topic.getTopicName(), partitionId, offset);
        if (message ==null){
            System.out.println("No message found...");
            return records;
        }
        commitOffset(offset + defaultLimit);
        records.addMessage(partitionId, List.of(message));
        return records;
    }

    @Override
    public void commitOffset(int offset) {
        offsetStore.updateOffset(topic.getTopicName(), consumerId, partitionId, offset);
    }
}
