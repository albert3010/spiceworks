package practice_lld.top25.lld.practice.kafka;

import lombok.AllArgsConstructor;
import lombok.Getter;
import practice_lld.top25.lld.practice.kafka.consumer.entity.OffsetStore;
import practice_lld.top25.lld.practice.kafka.consumer.entity.PartitionAssignment;
import practice_lld.top25.lld.practice.kafka.producer.entity.TopicRegistry;

import java.util.Map;

@AllArgsConstructor
@Getter
public class KafkaConfig {

    TopicRegistry topicRegistry;
    OffsetStore offsetStore;

}
