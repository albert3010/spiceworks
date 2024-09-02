package practice_lld.top25.lld.kafka;

import practice_lld.top25.lld.kafka.consumer.InMemoryOffsetStore;
import practice_lld.top25.lld.kafka.consumer.KafkaConsumer;
import practice_lld.top25.lld.kafka.consumer.RoundRobinPartitionAssignment;
import practice_lld.top25.lld.kafka.entity.Message;
import practice_lld.top25.lld.kafka.producer.KafkaProducer;
import practice_lld.top25.lld.kafka.producer.TopicRegistryImp;
import practice_lld.top25.lld.kafka.producer.entity.Partitioner;
import practice_lld.top25.lld.kafka.entity.Topic;
import practice_lld.top25.lld.kafka.producer.entity.TopicRegistry;
import practice_lld.top25.lld.kafka.consumer.entity.ConsumerRecords;
import practice_lld.top25.lld.kafka.consumer.entity.OffsetStore;
import practice_lld.top25.lld.kafka.consumer.entity.PartitionAssignment;
import practice_lld.top25.lld.kafka.producer.RoundRobinPartitioner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KafkaApplication {
    public static void main(String[] args) throws Exception {
        Topic<String, Integer> order = new Topic("order", 3);
        Topic<String, Integer> payment = new Topic("payment", 3);

        TopicRegistry<String, Integer> topicRegistry = new TopicRegistryImp<>();
        topicRegistry.addTopic(order);
        topicRegistry.addTopic(payment);

        OffsetStore offsetStore = new InMemoryOffsetStore();

        Map<String, PartitionAssignment> partitionAssignments = new HashMap<>();
        PartitionAssignment partitionAssignmentOrder = new RoundRobinPartitionAssignment(order.getPartitions(), List.of("c1", "c2", "c3"));
        PartitionAssignment partitionAssignmentPayment = new RoundRobinPartitionAssignment(payment.getPartitions(), List.of("c4", "c5", "c6"));
        partitionAssignments.put(order.getTopicName(), partitionAssignmentOrder);
        partitionAssignments.put(payment.getTopicName(), partitionAssignmentPayment);

        KafkaConfig kafkaConfig = new KafkaConfig(topicRegistry, offsetStore, partitionAssignments);

        MessageBroker<String, Integer> messageBroker = MessageBroker.getInstance(kafkaConfig);

        // producer
        Partitioner<String, Integer> partitioner = new RoundRobinPartitioner(3);

        KafkaProducer<String, Integer> producer = new KafkaProducer<>(messageBroker, partitioner);
        Message<String, Integer> message1 = new Message<>("order1", 123);
        Message<String, Integer> message2 = new Message<>("order2", 124);
        Message<String, Integer> message3 = new Message<>("order3", 125);
        Message<String, Integer> message4 = new Message<>("order4", 126);


        producer.sendMessage("order", message1);
        producer.sendMessage("order", message2);
        producer.sendMessage("order", message3);
        producer.sendMessage("order", message4);

        Message<String, Integer> p1 = new Message<>("payment1", 2123);
        Message<String, Integer> p2 = new Message<>("payment2", 2124);
        Message<String, Integer> p3 = new Message<>("payment3", 2125);
        Message<String, Integer> p4 = new Message<>("payment4", 2126);

        producer.sendMessage("payment", p1);
        producer.sendMessage("payment", p2);
        producer.sendMessage("payment", p3);
        producer.sendMessage("payment", p4);


        // consumer order
        KafkaConsumer kafkaConsumer = new KafkaConsumer<>(order, messageBroker);
        getMessagesFromConsumer(kafkaConsumer, payment, messageBroker);
        getMessagesFromConsumer(kafkaConsumer, payment, messageBroker);

        // consumer payment
        kafkaConsumer = new KafkaConsumer<>(payment, messageBroker);
        getMessagesFromConsumer(kafkaConsumer, payment, messageBroker);
        getMessagesFromConsumer(kafkaConsumer, payment, messageBroker);
    }

    private static void getMessagesFromConsumer(KafkaConsumer kafkaConsumer, Topic<String, Integer> payment, MessageBroker<String, Integer> messageBroker) {
        ConsumerRecords<String, Integer> records = kafkaConsumer.consumeMessage();
        Map<Integer, List<Message<String, Integer>>> recordsMap = records.getRecords();
        if(recordsMap.keySet().size() ==0) {
            System.out.println("No message found");
        }
        System.out.println("Topic : "+records.getTopicName());
        for (int partitionId : recordsMap.keySet()) {
            System.out.println("partitionId : " + partitionId);
            System.out.println("Message : " + recordsMap.get(partitionId).get(0));
            System.out.println("--------------");
        }
    }
}
