package practice_lld.top25.lld.kafka.consumer.entity;

import practice_lld.top25.lld.kafka.entity.Partition;

import java.util.Map;

public interface PartitionAssignment<K, V>{

    Partition<K, V> getNextPartition();

    void assignPartition(String consumerId, Partition<K, V> partition);

    Partition<K, V> getAssignPartition(int consumerId);

    Map<String, Partition<K, V>>  getAllAssigned();

}
