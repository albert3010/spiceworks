package practice_lld_2023.machine_coding.CacheApp.storage;

import java.util.HashMap;
import java.util.Map;

public class InMemoryStorage<Key, Value> implements IStorage<Key, Value> {
    private Map<Key, Value> map;
    private Integer capacity;

    public InMemoryStorage(int capacity) {
        this.map = new HashMap<>();
        this.capacity = capacity;
    }

    public Value get(Key key)  {
        return  map.get(key);
    }

    public void put(Key key, Value value) {
        map.put(key, value);
    }

    public Value removeKey(Key key) {
        return map.remove(key);
    }

    public boolean isStorageFull() {
        return map.size() >= capacity;
    }

}
