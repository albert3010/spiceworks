package practice_lld.top25.lld.kafka.consumer.entity;

public interface OffsetStore {

    int getOffset(String topicName, String consumerId, int partitionId);

    void updateOffset(String topicName, String consumerId, int partitionId, int offset);

}
