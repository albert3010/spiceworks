package practice_lld_2023.lld_2022.MultiLevelCache.provider;

import lombok.AllArgsConstructor;
import practice_lld_2023.lld_2022.MultiLevelCache.model.CacheLevelData;
import practice_lld_2023.lld_2022.MultiLevelCache.model.ReadResponse;
import practice_lld_2023.lld_2022.MultiLevelCache.model.WriteResponse;

import java.util.List;

@AllArgsConstructor
public class DefaultLevelCache<Key, Value> implements ILevelCache<Key, Value> {

    private final CacheLevelData cacheLevelData;
    private final CacheProvider<Key, Value> cacheProvider;
    private final ILevelCache<Key, Value> nextLevelCache; // chain

    @Override
    public WriteResponse set(Key key, Value value) {
        Double writeTime = cacheLevelData.getReadTime();
        Value v = cacheProvider.get(key);
        if (v == null || v != value) {
            cacheProvider.set(key, value);
            writeTime += cacheLevelData.getWriteTime();
            writeTime += nextLevelCache.set(key, value).getWriteTime();
        }

        return new WriteResponse(writeTime);
    }

    @Override
    public ReadResponse<Value> get(Key key) {
        Double readTime = cacheLevelData.getReadTime();
        Value value = cacheProvider.get(key);
        ReadResponse<Value> response = new ReadResponse<>(value, readTime);
        if (value == null) {
            response = nextLevelCache.get(key);
            readTime += response.getReadTime();

            cacheProvider.set(key, response.getValue());
            readTime += cacheLevelData.getWriteTime();
        }
        return new ReadResponse<>(response.getValue(), readTime);
    }

    @Override
    public List<Double> getUsage() {
        return null;
    }
}
