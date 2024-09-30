package evictionPolicy;

public interface EvictionPolicy {

    void recordUsage(String key);

    String getEvictionKey();

    void removeKey(String key);

    boolean isCapacityFull();
}
