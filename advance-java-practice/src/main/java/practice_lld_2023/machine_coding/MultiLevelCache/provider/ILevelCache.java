package practice_lld_2023.machine_coding.MultiLevelCache.provider;

import practice_lld_2023.machine_coding.MultiLevelCache.model.ReadResponse;
import practice_lld_2023.machine_coding.MultiLevelCache.model.WriteResponse;

import java.util.List;

public interface ILevelCache<Key, Value> {

    WriteResponse set(Key key, Value value);

    ReadResponse<Value> get(Key key);

    List<Double> getUsage();
}
