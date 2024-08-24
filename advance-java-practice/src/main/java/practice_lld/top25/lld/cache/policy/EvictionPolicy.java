package practice_lld.top25.lld.cache.policy;

public interface EvictionPolicy<K> {

    void keyAccessed(K k);

    K evictKey();
}
