package practice_lld_2023.lld_2022.MessageQueue;

import practice_lld_2023.lld_2022.MessageQueue.consumer.Consumer;

import java.util.*;

public class QueueService {

    private Map<String, Set<Consumer>> topicToConsumerMap;
    private Map<String, Topic> topicMap;
    private Map<String, Map<Integer, Integer>> topicConsumerOffset;

    public QueueService() {
        this.topicToConsumerMap = new HashMap<>();
        this.topicMap = new HashMap<>();
        this.topicConsumerOffset = new HashMap<>();
    }

    public void addTopic(Topic topic) {
        topicMap.put(topic.getTopicName(), topic);
    }

    public void publishMessage(String topic, String message) {
        topicMap.get(topic).addEvent(message);
        for (Consumer consumer : topicToConsumerMap.get(topic)) {
            consumer.consume(message, topic);
        }
    }

    public void subscribeTopic(String topic, Consumer consumer) {
        topicToConsumerMap.putIfAbsent(topic, new HashSet<>());
        topicToConsumerMap.get(topic).add(consumer);
        topicConsumerOffset.put(topic, new HashMap<>());
        topicConsumerOffset.get(topic).putIfAbsent(consumer.getConsumerId(), -1);
        consumeExistingData(topic, consumer);
    }

    private void consumeExistingData(String topic, Consumer consumer) {
        Topic topicData = topicMap.get(topic);
        int offset = topicConsumerOffset.get(topic).get(consumer.getConsumerId()) + 1;
        List<String> data = topicData.getData();
        int dataSize = data.size();
        while (offset < dataSize) {
            String message = topicData.getData().get(offset);
            consumer.consume(message, topic);
            offset++;
        }
    }

    public void unSubscribeTopic(String topic, Consumer consumer) throws Exception {
        Set<Consumer> consumers = topicToConsumerMap.get(topic);
        if (Objects.isNull(consumers)) {
            throw new Exception("Consumer not present");
        }
        consumers.remove(consumer);

    }

    public void consumerAck(Integer consumerId, String topic) {
        topicConsumerOffset.putIfAbsent(topic, new HashMap<>());
        Map<Integer, Integer> consumerOffsetMap = topicConsumerOffset.get(topic);

        if (Objects.nonNull(consumerOffsetMap)) {
            consumerOffsetMap.put(consumerId,
                    consumerOffsetMap.getOrDefault(consumerId, 0) + 1);
        }
    }

}
