package practice_lld_2023.design_patterns.filter;

import java.util.List;

public interface Criteria {

    public List<Employee> meetCriteria(List<Employee> employees);
}
