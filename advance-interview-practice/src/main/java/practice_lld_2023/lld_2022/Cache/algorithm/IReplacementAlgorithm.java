package practice_lld_2023.lld_2022.Cache.algorithm;

public interface IReplacementAlgorithm<TKey> {

    TKey  getEvictionKey();
    void removeKey(TKey key);
    void recordUsage(TKey key);
}
