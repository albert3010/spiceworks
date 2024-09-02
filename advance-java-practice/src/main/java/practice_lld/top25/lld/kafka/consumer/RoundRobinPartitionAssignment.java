package practice_lld.top25.lld.kafka.consumer;

import lombok.Getter;
import practice_lld.top25.lld.kafka.entity.Partition;
import practice_lld.top25.lld.kafka.consumer.entity.PartitionAssignment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class RoundRobinPartitionAssignment<K, V> implements PartitionAssignment<K, V> {
    private final Map<String, Partition<K, V>> partitionAssignment = new HashMap<>();
    private int lastAssignedPartitionIndex = 0;
    private List<Partition<K, V>> partitions;
    private int numPartitions;

    public RoundRobinPartitionAssignment(List<Partition<K, V>> partitions, List<String> consumers) throws Exception {
        this.numPartitions = partitions.size();
        this.partitions = partitions;
        if (consumers.size() > partitions.size()) throw new Exception("consumers is more than partitions");
        for (String consumerId : consumers) {
            partitionAssignment.put(consumerId, getNextPartition());
        }
    }

    @Override
    public Partition<K, V> getNextPartition() {
        if (lastAssignedPartitionIndex == numPartitions) return null;
        int k = (lastAssignedPartitionIndex + 1) % numPartitions;
        lastAssignedPartitionIndex++;
        return partitions.get(k);
    }

    @Override
    public void assignPartition(String consumerId, Partition<K, V> partition) {
        partitionAssignment.put(consumerId, partition);
    }

    @Override
    public Partition<K, V> getAssignPartition(int consumerId) {
        return partitionAssignment.get(consumerId);
    }

    public Map<String, Partition<K, V>> getAllAssigned() {
        return partitionAssignment;
    }
}
