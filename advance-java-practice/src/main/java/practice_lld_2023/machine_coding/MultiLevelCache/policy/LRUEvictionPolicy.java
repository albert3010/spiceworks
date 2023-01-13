package practice_lld_2023.machine_coding.MultiLevelCache.policy;

import java.util.LinkedList;

public class LRUEvictionPolicy<Key> implements EvictionPolicy<Key> {

    private LinkedList<Key> UsageTracker;

    public LRUEvictionPolicy() {
        UsageTracker = new LinkedList<>();
    }


    public Key getEvictionKey() {
        return UsageTracker.getLast();
    }


    public void recordUsage(Key key) {
        if (key != null) {
            removeKey(key);
            UsageTracker.addFirst(key);
        }

    }


    public Key removeKey(Key key) {
        UsageTracker.remove(key);
        return key;
    }
}
