package company.bankbazar.commommanager;

/**
 * Created by OMPRAKASH on 9/30/2016.
 */
public class Employee {

    private Integer empId;
    private String name;

    public Employee(Integer empId, String name) {
        this.empId = empId;
        this.name = name;
    }

    public Integer getEmpId() {
        return empId;
    }

    public String getName() {
        return name;
    }
}
