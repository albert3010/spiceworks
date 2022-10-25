package model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Employee {
    private int id;
    private String name;
    private Address address;
    private Integer managerId;
    private List<Integer> reportees;

    public Employee(int id, String name, Address address, Integer managerId) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.managerId = managerId;
        this.reportees = new ArrayList<>();
    }
    public Employee(Input input) {
        this.id = input.getId();
        this.name = input.getName();
        this.address = new Address(input.getAddress());;
        this.managerId = input.getManagerId();
        this.reportees = new ArrayList<>();
    }

    public void addReportee(int empId){
        reportees.add(empId);
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }
}
