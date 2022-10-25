package practice_lld_2023.lld_2022.MultiLevelCache.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CacheLevelData {

    private Double readTime;
    private Double writeTime;

}
