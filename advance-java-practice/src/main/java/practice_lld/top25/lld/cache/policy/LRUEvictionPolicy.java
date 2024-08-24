package practice_lld.top25.lld.cache.policy;

import java.util.LinkedHashSet;

public class LRUEvictionPolicy<K> implements EvictionPolicy<K>{

    private LinkedHashSet<K> set = new LinkedHashSet<>();

    @Override
    public void keyAccessed(K k) {
        set.remove(k);
        set.add(k);
    }

    @Override
    public K evictKey() {
        K k = set.iterator().next();
        set.remove(k);
        return k;
    }
}
