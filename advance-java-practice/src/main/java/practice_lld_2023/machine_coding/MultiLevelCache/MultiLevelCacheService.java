package practice_lld_2023.machine_coding.MultiLevelCache;

import practice_lld_2023.machine_coding.MultiLevelCache.Service.CacheService;
import practice_lld_2023.machine_coding.MultiLevelCache.Storage.IStorage;
import practice_lld_2023.machine_coding.MultiLevelCache.Storage.InMemoryStorage;
import practice_lld_2023.machine_coding.MultiLevelCache.model.CacheLevelData;
import practice_lld_2023.machine_coding.MultiLevelCache.model.ReadResponse;
import practice_lld_2023.machine_coding.MultiLevelCache.model.WriteResponse;
import practice_lld_2023.machine_coding.MultiLevelCache.policy.EvictionPolicy;
import practice_lld_2023.machine_coding.MultiLevelCache.policy.LRUEvictionPolicy;
import practice_lld_2023.machine_coding.MultiLevelCache.provider.CacheProvider;
import practice_lld_2023.machine_coding.MultiLevelCache.provider.DefaultLevelCache;
import practice_lld_2023.machine_coding.MultiLevelCache.provider.EmptyLevelCache;

public class MultiLevelCacheService {

    public static void main(String[] args) {

        EmptyLevelCache emptyLevelCache = new EmptyLevelCache();

        IStorage storage = new InMemoryStorage(3);
        EvictionPolicy evictionPolicy = new LRUEvictionPolicy();
        CacheProvider cacheProvider = new CacheProvider(evictionPolicy, storage);

        CacheLevelData cacheLevelData = new CacheLevelData(50.0,300.0);
        DefaultLevelCache L3 = new DefaultLevelCache(cacheLevelData, cacheProvider, emptyLevelCache);


        IStorage storage2 = new InMemoryStorage(2);
        EvictionPolicy evictionPolicy2 = new LRUEvictionPolicy();
        CacheProvider cacheProvider2 = new CacheProvider(evictionPolicy2, storage2);

        CacheLevelData cacheLevelDataL2 = new CacheLevelData(20.0,200.0);
        DefaultLevelCache L2 = new DefaultLevelCache(cacheLevelDataL2, cacheProvider2, L3);


        IStorage storage1 = new InMemoryStorage(1);
        EvictionPolicy evictionPolicy1 = new LRUEvictionPolicy();
        CacheProvider cacheProvider1 = new CacheProvider(evictionPolicy1, storage1);
        CacheLevelData cacheLevelDataL1 = new CacheLevelData(5.0,100.0);
        DefaultLevelCache L1 = new DefaultLevelCache(cacheLevelDataL1, cacheProvider1, L2);

        CacheService cacheService = new CacheService(L1);

        WriteResponse writeResponse;
        ReadResponse readResponse;
        writeResponse = cacheService.set("k1", "v1");
        writeResponse = cacheService.set("k1", "v1");
        writeResponse= cacheService.set("k2", "v2");
        readResponse= cacheService.get("k1");

        int a=0;







    }


}
