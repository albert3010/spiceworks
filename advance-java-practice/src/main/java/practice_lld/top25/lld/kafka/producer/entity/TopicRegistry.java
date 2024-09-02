package practice_lld.top25.lld.kafka.producer.entity;

import practice_lld.top25.lld.kafka.entity.Topic;

public interface TopicRegistry<K, V>{

    void addTopic(Topic<K, V> topic);

    Topic<K, V> getTopic(String topicName);
}
