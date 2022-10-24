package practice_lld_2023.lld_2022.MultiLevelCache.Storage;

import java.util.HashMap;
import java.util.Map;

public class InMemoryStorage<Key, Value> implements IStorage<Key, Value> {
    private final Map<Key, Value> map;
    private final Integer capacity;

    public InMemoryStorage(Integer capacity) {
        this.map = new HashMap<>();
        this.capacity = capacity;
    }

    public Value set(Key key, Value value) {
        map.put(key, value);
        return value;
    }
    public Value remove(Key key){
        return map.remove(key);
    }


    public Value get(Key key) {
        return map.get(key);
    }

    public Integer totalUsage() {
        return map.size();
    }

    public Boolean isStorageFull() {
        return totalUsage() >= capacity;
    }

}
