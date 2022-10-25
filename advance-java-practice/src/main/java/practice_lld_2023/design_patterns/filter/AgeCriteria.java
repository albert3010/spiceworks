package practice_lld_2023.design_patterns.filter;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class AgeCriteria implements Criteria{
    private int age =30;

    public AgeCriteria(int age) {
        this.age = age;
    }

    @Override
    public List<Employee> meetCriteria(List<Employee> employees) {
        return employees.stream()
                .filter(e -> e.getAge() >= age)
                .collect(Collectors.toList());
    }
}
