package practice_lld_2023.machine_coding.Cache.algorithm;

public interface IReplacementAlgorithm<TKey> {

    TKey  getEvictionKey();
    void removeKey(TKey key);
    void recordUsage(TKey key);
}
