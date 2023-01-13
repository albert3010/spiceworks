package practice_lld_2023.machine_coding.CacheApp.provider;

import practice_lld_2023.machine_coding.CacheApp.eviction_policy.IEvictionPolicy;
import practice_lld_2023.machine_coding.CacheApp.storage.IStorage;
import lombok.extern.log4j.Log4j;

import java.util.Objects;

@Log4j
public class CacheProvider<Key, Value> {

    private IStorage storage;
    private IEvictionPolicy evictionPolicy;

    public CacheProvider(IStorage storage, IEvictionPolicy evictionPolicy) {
        this.storage = storage;
        this.evictionPolicy = evictionPolicy;
    }

    public Value get(Key key) throws Exception {

        Value value = (Value) storage.get(key);
        if (Objects.nonNull(value)) {
            evictionPolicy.trackKey(key);
        } else {
            log.info("Key not found {} " + key);
            throw new Exception("Key not found -> " + key);
        }
        return value;
    }

    public void put(Key key, Value value) {
        Value existingVal = (Value) storage.get(key);
        if (Objects.isNull(existingVal)) {
            if (storage.isStorageFull()) {
                log.info("Storage is full while updating key :" + key);
                System.out.println("Storage is full while updating key :" + key);
                Key evictKey = (Key) evictionPolicy.evictKey();
                storage.removeKey(evictKey);
            }
        }
        storage.put(key, value);
        evictionPolicy.trackKey(key);
    }

}
