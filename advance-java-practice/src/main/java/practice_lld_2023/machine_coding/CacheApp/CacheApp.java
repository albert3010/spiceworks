package practice_lld_2023.machine_coding.CacheApp;

import practice_lld_2023.machine_coding.CacheApp.eviction_policy.LRUEvictionPolicy;
import practice_lld_2023.machine_coding.CacheApp.provider.CacheProvider;
import practice_lld_2023.machine_coding.CacheApp.service.CacheService;
import practice_lld_2023.machine_coding.CacheApp.storage.InMemoryStorage;

public class CacheApp {

    public static void main(String[] args) throws Exception {

        InMemoryStorage storage = new InMemoryStorage(3);
        LRUEvictionPolicy lruEvictionPolicy = new LRUEvictionPolicy();

        CacheProvider cacheProvider = new CacheProvider(storage, lruEvictionPolicy);

        CacheService cacheService = new CacheService(cacheProvider);


        cacheService.put("k1", "v1");
        cacheService.put("k2", "v2");
        cacheService.put("k3", "v3");
        cacheService.put("k1", "v5");
        cacheService.put("k4", "v4");

        System.out.println(cacheService.get("k2"));
//        System.out.println(cacheService.get("k2"));
//        System.out.println(cacheService.get("k1"));



    }
}
