package practice_lld_2023.design_patterns.filter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilterTest {

    public static void main(String[] args) {
        Criteria ageCriteria = new AgeCriteria(30);
        Criteria femaleCriteria = new FemaleCriteria();
        Criteria maleCriteria = new MaleCriteria();
        Criteria maleAgeCriteria = new AndCriteria(maleCriteria, ageCriteria);
        Criteria femaleAgeCriteria = new AndCriteria(femaleCriteria, ageCriteria);

        Employee employee1 = new Employee("harry", "Male", 30, false);
        Employee employee2 = new Employee("sonal", "Female", 35, false);
        Employee employee4 = new Employee("priya", "Female", 31, false);
        Employee employee3 = new Employee("Don", "male", 45, false);
        List<Employee> employees = Arrays.asList(employee1, employee2, employee3, employee4);

        printNames(femaleAgeCriteria.meetCriteria(employees));
        printNames(maleAgeCriteria.meetCriteria(employees));

    }

    private static void printNames(List<Employee> employees) {
        employees.stream().forEach(e -> System.out.println(e.getName()));
    }
}
