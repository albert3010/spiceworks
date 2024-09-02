package practice_lld.top25.lld.kafka.consumer;

import practice_lld.top25.lld.kafka.consumer.entity.OffsetStore;

import java.util.HashMap;
import java.util.Map;

public class InMemoryOffsetStore implements OffsetStore {
    Map<String, Map<String, Map<Integer, Integer>>> store = new HashMap<>();

    @Override
    public int getOffset(String topicName, String consumerId, int partitionId) {
        return store.getOrDefault(topicName, new HashMap<>())
                .getOrDefault(consumerId, new HashMap<>())
                .getOrDefault(partitionId, 0);
    }

    @Override
    public void updateOffset(String topicName, String consumerId, int partitionId, int offset) {
        store.computeIfAbsent(topicName, k -> new HashMap<>())
                .computeIfAbsent(consumerId, k -> new HashMap<>())
                .put(partitionId, offset);
    }

}
