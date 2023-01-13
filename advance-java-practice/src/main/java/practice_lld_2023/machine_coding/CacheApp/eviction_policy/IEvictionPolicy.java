package practice_lld_2023.machine_coding.CacheApp.eviction_policy;

public interface IEvictionPolicy<Key> {

    Key evictKey();

    void trackKey(Key key);

}
