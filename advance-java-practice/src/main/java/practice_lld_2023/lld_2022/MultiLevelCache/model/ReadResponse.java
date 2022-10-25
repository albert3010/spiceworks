package practice_lld_2023.lld_2022.MultiLevelCache.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ReadResponse<Value> {
    Value value;
    private Double readTime;
}
