package practice_lld_2023.machine_coding.CacheApp.storage;

public interface IStorage<Key, Value> {

    Value get(Key key) ;
    void put(Key key, Value value);
    Value removeKey(Key key);
    boolean isStorageFull();
}
