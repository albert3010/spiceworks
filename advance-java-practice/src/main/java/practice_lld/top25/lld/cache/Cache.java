package practice_lld.top25.lld.cache;

import practice_lld.top25.lld.cache.exception.NotFoundException;
import practice_lld.top25.lld.cache.policy.EvictionPolicy;
import practice_lld.top25.lld.cache.storage.Storage;

public class Cache <K, V>{

    EvictionPolicy<K> evictionPolicy;
    Storage<K, V> storage;

    public Cache(EvictionPolicy<K> evictionPolicy, Storage<K, V> storage) {
        this.evictionPolicy = evictionPolicy;
        this.storage = storage;
    }

    public void put(K k, V v) {
        try {
            storage.get(k);
            evictionPolicy.keyAccessed(k);
            storage.add(k, v);
        }catch (NotFoundException e){
            if(storage.isFull()){
                K key = evictionPolicy.evictKey();
                storage.remove(key);
            }
            storage.add(k, v);
            evictionPolicy.keyAccessed(k);
        }
    }

    public V get(K k){
        evictionPolicy.keyAccessed(k);
        return storage.get(k);
    }

}
