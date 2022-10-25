package practice_lld_2023.lld_2022.Cache.algorithm;

import java.util.LinkedList;

public class LRUReplacementAlgorithm<TKey> implements IReplacementAlgorithm<TKey> {

    private LinkedList<TKey> UsageTracker;

    public LRUReplacementAlgorithm() {
        this.UsageTracker = new LinkedList<>();
    }

    public TKey getEvictionKey() {
        TKey keyToReplace = this.UsageTracker.getLast();
        return keyToReplace;
    }

    @Override
    public void removeKey(TKey key) {
        this.UsageTracker.remove(key);
    }

    public void recordUsage(TKey key) {
        removeKey(key);
        this.UsageTracker.addFirst(key);
    }
}
