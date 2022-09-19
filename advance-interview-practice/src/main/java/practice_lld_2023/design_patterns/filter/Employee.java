package practice_lld_2023.design_patterns.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Data
@Getter
public class Employee {

    String name;
    String gender;
    int age;
    boolean isRetired;

}
