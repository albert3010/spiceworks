package evictionPolicy;

import java.util.LinkedList;

public class LRUEvictionPolicy implements EvictionPolicy {
    int capacity;
    private LinkedList<String> keyUsageStore;

    public LRUEvictionPolicy(int capacity) {
        this.keyUsageStore = new LinkedList<>();
        this.capacity = capacity;
    }

    @Override
    public void recordUsage(String key) {
        removeKey(key);
        keyUsageStore.add(key);

    }

    @Override
    public String getEvictionKey() {
        if(keyUsageStore.size() ==0){
            return  null;
        }
        return keyUsageStore.getFirst();
    }

    @Override
    public void removeKey(String key) {
        int size = keyUsageStore.size();
        for (int i = 0; i < size; i++) {
            if (keyUsageStore.get(i).equals(key)) {
                keyUsageStore.remove(i);
                break;
            }
        }
    }

    @Override
    public boolean isCapacityFull() {
        return keyUsageStore.size() >= capacity;
    }

}
