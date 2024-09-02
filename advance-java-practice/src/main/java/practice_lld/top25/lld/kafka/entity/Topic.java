package practice_lld.top25.lld.kafka.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class Topic<K, V> {
    String topicName;
    List<Partition<K, V>> partitions;

    public Topic(String topicName, int partitionCount) {
        this.topicName = topicName;
        this.partitions = new ArrayList<>();
        for (int pId = 1; pId <= partitionCount; pId++) {
            partitions.add(new Partition<>(pId));
        }
    }
}
