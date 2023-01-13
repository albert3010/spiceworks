package practice_lld_2023.machine_coding.CacheApp.eviction_policy;

import java.util.ArrayList;
import java.util.List;

public class LRUEvictionPolicy<Key> implements IEvictionPolicy<Key> {

    private List<Key> list;

    public LRUEvictionPolicy() {
        this.list = new ArrayList<>();
    }

    public Key evictKey() {
        if (list.size() > 0) {
            Key key_ = list.get(0);
            list.remove(0);
            return key_;
        } else {
            return null;
        }
    }

    public void trackKey(Key key) {
        if (list.contains(key)) {
            list.remove(key);
        }
        list.add(key);
    }
}
