package practice_lld_2023.lld_2022.MultiLevelCache.policy;

public interface EvictionPolicy<Key> {

    Key getEvictionKey();

    void recordUsage(Key key);

    Key removeKey(Key key);

}
