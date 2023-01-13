package practice_lld_2023.machine_coding.MultiLevelCache.policy;

public interface EvictionPolicy<Key> {

    Key getEvictionKey();

    void recordUsage(Key key);

    Key removeKey(Key key);

}
