package practice.company.bankbazar.commommanager;

import lombok.Getter;

import java.util.HashMap;
import java.util.List;

/**
 * Created by OMPRAKASH on 9/30/2016.
 */
@Getter
public class ManagerEmpLCA {

    List<People> peoples;

    public ManagerEmpLCA(List<People> peoples) {
        this.peoples = peoples;
    }

    public void addPeople(People people){
        peoples.add(people);
    }

    public String getCommonManager(Employee employee1, Employee employee2) {

        HashMap<Employee, Employee> employeeManagerHashMap = new HashMap<>();

        HashMap<Employee, Boolean> employeeHashMap = new HashMap<>();


        for (People people : peoples) {

            Employee employee = people.getEmployee();
            Employee manger = people.getEmployeeManager();

            employeeManagerHashMap.put(employee, manger);
        }

        Integer id = employee1.getEmpId();
        Employee employeeTmp = employee1;

        while (id != 0) {

            employeeTmp = employeeManagerHashMap.get(employeeTmp);

            employeeHashMap.put(employeeTmp, true);
            id = employeeTmp.getEmpId();

        }

        employeeTmp = employee2;
        id = employee2.getEmpId();
        while (id != 0) {

            employeeTmp = employeeManagerHashMap.get(employeeTmp);

            if (employeeHashMap.get(employeeTmp)!=null && employeeHashMap.get(employeeTmp) )
                return employeeTmp.getName();
            id = employeeTmp.getEmpId();

        }

        return "No common manager";
    }

}
