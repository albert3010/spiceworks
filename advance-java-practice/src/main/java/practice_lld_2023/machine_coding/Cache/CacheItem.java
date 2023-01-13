package practice_lld_2023.machine_coding.Cache;

/**
 * An internal data structure representing a single item in an N-Way Set-Associative Cache
 */
public class CacheItem<TKey, TValue> {
    public TKey key;
    public TValue value;

    public CacheItem(TKey key, TValue value) {
        this.key = key;
        this.value = value;
    }
}
