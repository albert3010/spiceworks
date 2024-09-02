package practice_lld.top25.lld.kafka.producer;

import lombok.AllArgsConstructor;
import practice_lld.top25.lld.kafka.entity.Topic;
import practice_lld.top25.lld.kafka.producer.entity.TopicRegistry;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public class TopicRegistryImp<K,V> implements TopicRegistry<K,V> {

    Map<String, Topic<K,V>> topicMap;

    public TopicRegistryImp() {
        this.topicMap =  new HashMap<>();;
    }

    @Override
    public void addTopic(Topic<K, V> topic) {
        topicMap.put(topic.getTopicName(), topic);
    }

    @Override
    public Topic<K, V> getTopic(String topicName) {
        return topicMap.get(topicName);
    }
}
