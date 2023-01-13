package practice_lld_2023.machine_coding.MultiLevelCache.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ReadResponse<Value> {
    Value value;
    private Double readTime;
}
