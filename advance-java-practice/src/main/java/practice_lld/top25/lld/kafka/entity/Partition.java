package practice_lld.top25.lld.kafka.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;


@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Partition<K, V> {
    int partitionId;
    List<Message<K, V>> messages = new ArrayList<>();

    public Partition(int partitionId) {
        this.partitionId = partitionId;
    }

    public synchronized void addMessage(Message<K, V> message) {
        messages.add(message);
    }

    public synchronized Message<K, V> consume(int offset) {
        if (offset >= messages.size()) return null;
        return messages.get(offset);
    }
}
