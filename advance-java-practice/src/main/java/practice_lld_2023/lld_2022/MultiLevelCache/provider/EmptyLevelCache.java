package practice_lld_2023.lld_2022.MultiLevelCache.provider;

import lombok.AllArgsConstructor;
import practice_lld_2023.lld_2022.MultiLevelCache.model.CacheLevelData;
import practice_lld_2023.lld_2022.MultiLevelCache.model.ReadResponse;
import practice_lld_2023.lld_2022.MultiLevelCache.model.WriteResponse;

import java.util.List;

@AllArgsConstructor
public class EmptyLevelCache<Key, Value> implements ILevelCache<Key, Value> {

    @Override
    public WriteResponse set(Key key, Value value) {

        return new WriteResponse(0.0);
    }

    @Override
    public ReadResponse<Value> get(Key key) {
        return new ReadResponse<>(null, 0.0);
    }

    @Override
    public List<Double> getUsage() {
        return null;
    }
}
