package practice_lld.top25.lld.kafka.consumer;

import practice_lld.top25.lld.kafka.MessageBroker;
import practice_lld.top25.lld.kafka.entity.Message;
import practice_lld.top25.lld.kafka.entity.Partition;
import practice_lld.top25.lld.kafka.entity.Topic;
import practice_lld.top25.lld.kafka.consumer.entity.Consumer;
import practice_lld.top25.lld.kafka.consumer.entity.ConsumerRecords;
import practice_lld.top25.lld.kafka.consumer.entity.OffsetStore;
import practice_lld.top25.lld.kafka.consumer.entity.PartitionAssignment;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class KafkaConsumer<K, V> implements Consumer<K, V> {
    private String consumerId;
    private Topic topic;
    PartitionAssignment partitionAssignment;
    OffsetStore offsetStore;
    int defaultLimit = 1;

    public KafkaConsumer(Topic topic, MessageBroker messageBroker) {
        this.consumerId = UUID.randomUUID().toString();
        this.topic = topic;
        this.partitionAssignment = (PartitionAssignment) messageBroker.getPartitionAssignments().get(topic.getTopicName());
        this.offsetStore = messageBroker.getOffsetStore();
    }

    @Override
    public ConsumerRecords<K, V> consumeMessage() {
        ConsumerRecords records = new ConsumerRecords(topic.getTopicName());
        Map<String, Partition<K, V>> allAssigned = partitionAssignment.getAllAssigned();
        for (String consumerId : allAssigned.keySet()) {
            Partition<K, V> partition = allAssigned.get(consumerId);
            int pId = partition.getPartitionId();
            int offset = offsetStore.getOffset(topic.getTopicName(), consumerId, pId);
            Message<K, V> message = partition.consume(offset);
            if(message == null){
                continue;
            }
            commitOffset(consumerId, pId, offset + defaultLimit);
            records.addMessage(pId, List.of(message));
        }
        return records;
    }

    @Override
    public void commitOffset(String consumerId, int partitionId, int offset) {
        offsetStore.updateOffset(topic.getTopicName(), consumerId, partitionId, offset);
    }
}
