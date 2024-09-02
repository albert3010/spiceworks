package practice_lld.top25.lld.kafka.consumer.entity;

import lombok.Getter;
import practice_lld.top25.lld.kafka.entity.Message;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class ConsumerRecords<K, V> {
    private String topicName;
    private Map<Integer, List<Message<K, V>>> records;

    public ConsumerRecords(String topicName) {
        this.topicName = topicName;
        this.records = new HashMap<>();
    }

    public void addMessage(int partition, List<Message<K, V>> messages) {
        records.put(partition, messages);
    }
}
