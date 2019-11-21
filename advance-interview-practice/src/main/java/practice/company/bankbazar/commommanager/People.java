package practice.company.bankbazar.commommanager;

import lombok.Getter;

/**
 * Created by OMPRAKASH on 9/30/2016.
 */
@Getter

public class People {

    private Employee employeeManager;
    private Employee employee;

    public People(Employee employeeManager, Employee employee) {
        this.employeeManager = employeeManager;
        this.employee = employee;
    }

    public Employee getEmployeeManager() {
        return employeeManager;
    }

    public Employee getEmployee() {
        return employee;
    }
}
