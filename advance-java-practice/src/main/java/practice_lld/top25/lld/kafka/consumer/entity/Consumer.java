package practice_lld.top25.lld.kafka.consumer.entity;

public interface Consumer <K, V>{

    ConsumerRecords<K,V> consumeMessage();

    public void commitOffset(String consumerId, int partitionId, int offset);
}
