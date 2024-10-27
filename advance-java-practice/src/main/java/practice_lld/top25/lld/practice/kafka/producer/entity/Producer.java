package practice_lld.top25.lld.practice.kafka.producer.entity;

import practice_lld.top25.lld.practice.kafka.entity.Message;

public interface Producer<K, V> {

    void sendMessage(String topicName, Message<K, V> message);

}
