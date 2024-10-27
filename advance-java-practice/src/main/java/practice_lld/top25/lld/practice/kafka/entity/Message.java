package practice_lld.top25.lld.practice.kafka.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Message<K, V> {
    K key;
    V value;
}
