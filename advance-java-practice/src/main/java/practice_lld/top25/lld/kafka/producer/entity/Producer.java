package practice_lld.top25.lld.kafka.producer.entity;

import practice_lld.top25.lld.kafka.entity.Message;

public interface Producer<K, V> {

    void sendMessage(String topicName, Message<K, V> message);

}
