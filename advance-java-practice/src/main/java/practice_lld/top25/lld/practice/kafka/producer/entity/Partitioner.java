package practice_lld.top25.lld.practice.kafka.producer.entity;

public interface Partitioner <K, V>{

    int partition(String topicName, K key, V value);

}
