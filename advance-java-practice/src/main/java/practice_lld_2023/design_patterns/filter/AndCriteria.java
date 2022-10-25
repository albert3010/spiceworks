package practice_lld_2023.design_patterns.filter;

import lombok.Getter;

import java.util.List;

@Getter
public class AndCriteria implements Criteria {
    private Criteria[] criterias;

    public AndCriteria(Criteria... criterias) {
        this.criterias = criterias;
    }

    @Override
    public List<Employee> meetCriteria(List<Employee> employees) {
        List<Employee> filteredEmployees = employees;
        for (Criteria criteria : criterias) {
            filteredEmployees = criteria.meetCriteria(filteredEmployees);
        }
        return filteredEmployees;
    }
}
