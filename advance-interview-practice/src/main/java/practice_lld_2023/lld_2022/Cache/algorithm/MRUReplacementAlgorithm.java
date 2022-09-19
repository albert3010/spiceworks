package practice_lld_2023.lld_2022.Cache.algorithm;

import java.util.LinkedList;

public class MRUReplacementAlgorithm<TKey> implements IReplacementAlgorithm<TKey> {
    private LinkedList<TKey> UsageTracker;

    public MRUReplacementAlgorithm() {
        this.UsageTracker = new LinkedList<>();
    }

    public TKey getEvictionKey() {
        TKey keyToReplace = this.UsageTracker.getFirst();
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
