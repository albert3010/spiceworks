package practice_lld.top25.lld.practice.kafka.consumer.entity;

public interface Consumer <K, V>{

    ConsumerRecords<K,V> consumeMessage();

    public void commitOffset(int offset);
}
