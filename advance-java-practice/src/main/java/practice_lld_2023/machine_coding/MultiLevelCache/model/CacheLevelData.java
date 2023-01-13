package practice_lld_2023.machine_coding.MultiLevelCache.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CacheLevelData {

    private Double readTime;
    private Double writeTime;

}
