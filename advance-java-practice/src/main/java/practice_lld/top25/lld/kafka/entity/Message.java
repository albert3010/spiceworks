package practice_lld.top25.lld.kafka.entity;

public record Message<K, V> (
    K key,
    V value
){}
