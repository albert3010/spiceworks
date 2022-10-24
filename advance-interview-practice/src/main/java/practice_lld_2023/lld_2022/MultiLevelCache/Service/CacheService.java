package practice_lld_2023.lld_2022.MultiLevelCache.Service;

import lombok.AllArgsConstructor;
import practice_lld_2023.lld_2022.MultiLevelCache.model.ReadResponse;
import practice_lld_2023.lld_2022.MultiLevelCache.model.WriteResponse;
import practice_lld_2023.lld_2022.MultiLevelCache.provider.ILevelCache;

@AllArgsConstructor
public class CacheService<Key, Value> {

    private final ILevelCache<Key, Value> multiLevelCache;

    public ReadResponse<Value> get(Key key) {
        return multiLevelCache.get(key);
    }

    public WriteResponse set(Key key, Value value) {
        return multiLevelCache.set(key, value);
    }

}
