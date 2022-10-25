import model.Address;
import model.Employee;
import model.Input;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmpService {

    Map<Integer, Employee> empMap;
    Employee ceo;

    public EmpService() {
        this.empMap = new HashMap<>();
    }
    public void addEmploy(Input input){
        Employee  employee = new Employee(input);
        empMap.put(input.getId(), employee);
    }

    public void addEmployees(List<Input> inputList){
        for (Input input : inputList){
            addEmploy(input);
        }
        makeEmpTree();
    }

    private void makeEmpTree(){
        for ( Integer empId: empMap.keySet()){
            Employee emp = empMap.get(empId);
            Integer managerId = emp.getManagerId();
            if(managerId!=null){
                Employee manager = empMap.get(managerId);
                manager.addReportee(empId);
            }
        }
    }
    public Employee updateCEO(){
        for ( Integer empId: empMap.keySet()){
            Employee emp = empMap.get(empId);
            Integer managerId = emp.getManagerId();
            if(managerId==null){
                ceo = emp;
                break;
            }
        }
        return ceo;
    }

    public int maxDepth(){
        return maxDepthHelper(ceo);
    }

    private int maxDepthHelper(Employee emp) {
        if(emp==null) return 0;
        List<Integer> emps = emp.getReportees();
        int max =0;
        for(Integer empId :emps){
            max = Math.max(maxDepthHelper(empMap.get(empId)), max);
        }
        return max+1;
    }

    List<Employee> getAllManagers(){
        List<Employee> managers = new ArrayList<>();
        getAllManagersHelper(ceo, 1, managers);
        return managers;
    }

    List<Employee> getAllSeniorManagers(){
        List<Employee> managers = new ArrayList<>();
        getAllSeniorManagers(ceo, 2, managers);
        return managers;
    }

    private int getAllManagersHelper(Employee employee, int reporteesCount
            , List<Employee> employees) {
        if(employee==null) return 0;
        List<Integer> emps = employee.getReportees();
        int max =0;
        for(Integer empId :emps){
            max = Math.max(maxDepthHelper(empMap.get(empId)), max);
        }
        if(max == reporteesCount){
            employees.add(employee);
        }
        return max+1;
    }
    private int getAllSeniorManagers(Employee employee, int reporteesCount
            , List<Employee> employees) {
        if(employee==null) return 0;
        List<Integer> emps = employee.getReportees();
        int max =0;
        for(Integer empId :emps){
            int count = maxDepthHelper(empMap.get(empId));
            max = Math.max(count, max);
            if(count == reporteesCount){
                employees.add(employee);
            }
        }
        return max+1;
    }

    public List<Employee> directReportees(int empId){
        List<Employee> employees = new ArrayList<>();
        for(Integer id: empMap.get(empId).getReportees()){
            employees.add(empMap.get(id));
        }
        return employees;
    }
    public int totalReportees(int empId){
        return totalReporteesHelper(empMap.get(empId))-1;
    }
    public int totalReporteesHelper(Employee emp){
        if(emp==null) return 0;
        List<Integer> emps = emp.getReportees();
        int reportees =0;
        for(Integer empId :emps){
            reportees += totalReporteesHelper(empMap.get(empId));
        }
        return reportees+1;
    }

}
