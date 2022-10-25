package practice_lld_2023.lld_2022.MultiLevelCache.provider;

import practice_lld_2023.lld_2022.MultiLevelCache.Storage.IStorage;
import practice_lld_2023.lld_2022.MultiLevelCache.policy.EvictionPolicy;

public class CacheProvider<Key, Value> {
    private final EvictionPolicy evictionPolicy;
    private final IStorage iStorage;

    public CacheProvider(EvictionPolicy evictionPolicy, IStorage iStorage) {
        this.evictionPolicy = evictionPolicy;
        this.iStorage = iStorage;
    }

    Value get(Key key) {
        evictionPolicy.recordUsage(key);
        return (Value) iStorage.get(key);
    }

    void set(Key key, Value value) {
        if (iStorage.get(key) == null && iStorage.isStorageFull()) {
            Key evictionKey = (Key) evictionPolicy.getEvictionKey();

            iStorage.remove(evictionKey);
            evictionPolicy.removeKey(evictionKey);
        }
        iStorage.set(key, value);
        evictionPolicy.recordUsage(key);

    }

}
