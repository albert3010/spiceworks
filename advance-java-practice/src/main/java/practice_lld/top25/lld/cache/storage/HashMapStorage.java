package practice_lld.top25.lld.cache.storage;

import practice_lld.top25.lld.cache.exception.NotFoundException;
import practice_lld.top25.lld.cache.exception.StorageFullException;

import java.util.HashMap;
import java.util.Map;

public class HashMapStorage<K, V> implements Storage<K, V>{

    private int capacity;
    private Map<K, V> map;

    public HashMapStorage(int capacity){
        this.capacity = capacity;
        this.map = new HashMap<>();
    }

    public void add(K k, V v)  throws StorageFullException {
        if(isFull()) throw new StorageFullException("Storage full");
        map.put(k, v);
    }

    public void remove(K k) {
        if(map.get(k)==null) throw new NotFoundException("Key not found");
        map.remove(k);

    }

    public V get(K k) {
        if(map.get(k)==null) throw new NotFoundException("Key not found");
        return map.get(k);
    }

    public boolean isFull() {
        return map.size() == capacity;
    }
}
