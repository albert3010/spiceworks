package practice_lld_2023.machine_coding.CacheApp.service;

import practice_lld_2023.machine_coding.CacheApp.provider.CacheProvider;

public class CacheService<Key, Value> {
    private CacheProvider cacheProvider;

    public CacheService(CacheProvider cacheProvider) {
        this.cacheProvider = cacheProvider;
    }

    public Value get(Key key) throws Exception {
        return (Value) cacheProvider.get(key);
    }

    public void put(Key key, Value value) {
        cacheProvider.put(key, value);
    }
}
