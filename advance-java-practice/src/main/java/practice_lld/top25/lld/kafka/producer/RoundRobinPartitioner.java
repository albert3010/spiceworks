package practice_lld.top25.lld.kafka.producer;

import practice_lld.top25.lld.kafka.producer.entity.Partitioner;

import java.util.concurrent.atomic.AtomicInteger;

public class RoundRobinPartitioner<K, V> implements Partitioner<K, V> {
    private final AtomicInteger counter = new AtomicInteger(0);
    private int numPartitions = 10;

    public RoundRobinPartitioner(int numPartitions) {
        this.numPartitions = numPartitions;
    }

    @Override
    public int partition(String topicName, K key, V value) {
        return counter.getAndIncrement() % numPartitions;
    }
}
