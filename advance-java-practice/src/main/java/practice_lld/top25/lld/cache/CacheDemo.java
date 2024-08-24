package practice_lld.top25.lld.cache;

import practice_lld.top25.lld.cache.policy.EvictionPolicy;
import practice_lld.top25.lld.cache.policy.LRUEvictionPolicy;
import practice_lld.top25.lld.cache.storage.HashMapStorage;
import practice_lld.top25.lld.cache.storage.Storage;

public class CacheDemo {

    public static void main(String[] args) {

        Storage<String, Integer> storage = new HashMapStorage<>(4);
        EvictionPolicy<String> lruEvictionPolicy = new LRUEvictionPolicy<>();

        Cache<String, Integer> cache = new Cache<>(lruEvictionPolicy, storage);

        cache.put("cat", 11);
        cache.put("dog", 51);
        cache.put("pen", 71);
        cache.get("cat");
        cache.put("pencil", 91);
        cache.put("cup", 99);

        System.out.println(cache.get("pen"));
        System.out.println(cache.get("pen"));

    }
}
