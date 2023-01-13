package practice_lld_2023.machine_coding.MultiLevelCache.Storage;

public interface IStorage<Key, Value> {

    Value set(Key key, Value value);

    Value get(Key key);
    Value remove(Key key);
    Integer totalUsage();
    Boolean isStorageFull();

}
