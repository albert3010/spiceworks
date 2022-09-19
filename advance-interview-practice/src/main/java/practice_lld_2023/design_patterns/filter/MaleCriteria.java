package practice_lld_2023.design_patterns.filter;

import java.util.List;
import java.util.stream.Collectors;

public class MaleCriteria implements Criteria{

    @Override
    public List<Employee> meetCriteria(List<Employee> employees) {
        return employees.stream()
                .filter(e -> e.getGender().equalsIgnoreCase("Male"))
                .collect(Collectors.toList());
    }
}
