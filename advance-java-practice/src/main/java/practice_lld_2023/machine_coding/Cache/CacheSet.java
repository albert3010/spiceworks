package practice_lld_2023.machine_coding.Cache;

import practice_lld_2023.machine_coding.Cache.algorithm.IReplacementAlgorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * An internal data structure representing one set in a N-Way Set-Associative Cache
 */
public class CacheSet<TKey, TValue> {
    int Capacity;
    Map<TKey, CacheItem<TKey, TValue>> Store;
    public int Count;
    private IReplacementAlgorithm replacementAlgorithm;

    public CacheSet(int capacity, IReplacementAlgorithm replacementAlgorithm) {
        this.Capacity = capacity;
        this.Store = new HashMap<>();
        this.replacementAlgorithm = replacementAlgorithm;
        Count =0;
    }

    /** Gets the value associated with `key`. Throws if key not found. */
    public TValue get(TKey key) {
        // If the key is present, update the usage tracker
        if (this.containsKey(key)) {
            this.replacementAlgorithm.recordUsage(key);
        } else {
            throw new RuntimeException(String.format("The key '%s' was not found", key));
        }

        return this.Store.get(key).value;
    }

    /**
     * Adds the `key` to the cache with the associated value, or overwrites the existing key.
     * If adding would exceed capacity, an existing key is chosen to replace using an LRU algorithm
     * (NOTE: It is part of this exercise to allow for more replacement algos)
     */
    public void set(TKey key, TValue value) {
        CacheItem<TKey, TValue> item = this.Store.get(key);

        if (item !=null) {

            this.Store.get(key).value = value;

        } else {
            // If the set is at it's capacity
            if (this.Count == this.Capacity) {
                // Choose the Least-Recently-Used (LRU) item to replace, which will be at the tail of the usage tracker
                // TODO: Factor this logic out to allow for custom replacement algos
                TKey keyToReplace = (TKey) replacementAlgorithm.getEvictionKey();

                // Remove the existing key
                this.removeKey(keyToReplace);

            }

            this.Store.put(key, new CacheItem<>(key, value));
            this.Count++;

        }
        this.replacementAlgorithm.recordUsage(key);
    }

    /** Returns `true` if the given `key` is present in the set; otherwise, `false`. */
    public boolean containsKey(TKey key) {
        return this.Store.get(key) != null;
    }

    private void removeKey(TKey key) {
        CacheItem<TKey, TValue> item = this.Store.get(key);
        if (item != null) {
            replacementAlgorithm.removeKey(key);
            this.Store.remove(key);
            this.Count--;
        }
    }

}
