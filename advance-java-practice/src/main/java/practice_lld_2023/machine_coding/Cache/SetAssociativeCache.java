package practice_lld_2023.machine_coding.Cache;

import practice_lld_2023.machine_coding.Cache.algorithm.IReplacementAlgorithm;
import practice_lld_2023.machine_coding.Cache.algorithm.LRUReplacementAlgorithm;
import practice_lld_2023.machine_coding.Cache.algorithm.MRUReplacementAlgorithm;

/**
 * NOTE: You are free to modify anything below, except for class names and generic interface.
 * Other public interface changes may require updating one or more of the helper classes above
 * for test cases to run and pass.
 * <p>
 * A Set-Associative Cache data structure with fixed capacity.
 * <p>
 * - Data is structured into setCount # of setSize-sized sets.
 * - Every possible key is associated with exactly one set via a hashing algorithm
 * - If more items are added to a set than it has capacity for (i.e. > setSize items),
 *      a replacement victim is chosen from that set using an LRU algorithm.
 * <p>
 * NOTE: Part of the exercise is to allow for different kinds of replacement algorithms...
 */
public class SetAssociativeCache<TKey, TValue> {
    int Capacity;
    int SetSize;
    int SetCount;
    CacheSet<TKey, TValue>[] Sets;

    public SetAssociativeCache(int setCount, int setSize, IReplacementAlgorithm iReplacementAlgorithm) {
        this.SetCount = setCount;
        this.SetSize = setSize;
        this.Capacity = this.SetCount * this.SetSize;

        // Initialize the sets
        this.Sets = new CacheSet[this.SetCount];
        for (int i = 0; i < this.SetCount; i++) {
            if(iReplacementAlgorithm instanceof LRUReplacementAlgorithm){
                Sets[i] = new CacheSet<>(setSize, new LRUReplacementAlgorithm());
            }else {
                Sets[i] = new CacheSet<>(setSize, new MRUReplacementAlgorithm());
            }
        }
    }

    /** Gets the value associated with `key`. Throws if key not found. */
    public TValue get(TKey key) {
        int setIndex = this.getSetIndex(key);
        CacheSet<TKey, TValue> set = this.Sets[setIndex];
        return set.get(key);
    }

    /**
     * Adds the `key` to the cache with the associated value, or overwrites the existing key.
     * If adding would exceed capacity, an existing key is chosen to replace using an LRU algorithm
     * (NOTE: It is part of this exercise to allow for more replacement algos)
     */
    public void set(TKey key, TValue value) {
        int setIndex = this.getSetIndex(key);
        System.out.println(setIndex);
        CacheSet<TKey, TValue> set = this.Sets[setIndex];
        set.set(key, value);
    }

    /** Returns the count of items in the cache */
    public int getCount() {
        int count = 0;
        for (int i = 0; i < this.Sets.length; i++) {
            count += this.Sets[i].Count;
        }
        return count;
    }

    /** Returns `true` if the given `key` is present in the set; otherwise, `false`. */
    public boolean containsKey(TKey key) {
        int setIndex = this.getSetIndex(key);
        CacheSet<TKey, TValue> set = this.Sets[setIndex];
        return set.containsKey(key);
    }

    /** Maps a key to a set */
    private int getSetIndex(TKey key) {
        int setIndex = Math.abs(key.hashCode()) % this.SetCount;
        return setIndex;

    }
}
