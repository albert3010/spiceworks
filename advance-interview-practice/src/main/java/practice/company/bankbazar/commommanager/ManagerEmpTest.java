package practice.company.bankbazar.commommanager;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by OMPRAKASH on 9/30/2016.
 */
public class ManagerEmpTest {

    @Test
    public void getCommonManagerTest(){

        Employee employee0 = new Employee(0, "board");
        Employee employee1 = new Employee(1,"tarun");
        Employee employee2 = new Employee(2,"jawahar");
        Employee employee3 = new Employee(3,"surender");
        Employee employee4 = new Employee(4,"satish");
        Employee employee5 = new Employee(5,"rahul");
        Employee employee6 = new Employee(6,"amit");
        Employee employee7 = new Employee(7,"ankit");
        Employee employee8 = new Employee(8,"aveenish");
        Employee employee9 = new Employee(9,"supan");
        Employee employee10 = new Employee(10,"deepak");
        Employee employee11 = new Employee(11,"om");
        Employee employee12 = new Employee(12,"shree");


        People people0 = new People(employee0, employee1);
        People people1 = new People(employee1, employee2);
        People people2 = new People(employee1, employee3);
        People people3 = new People(employee2, employee4);
        People people4 = new People(employee2, employee5);
        People people5 = new People(employee3, employee6);
        People people6 = new People(employee3, employee7);
        People people7 = new People(employee4, employee8);
        People people8 = new People(employee5, employee9);
        People people9 = new People(employee5, employee10);
        People people10 = new People(employee9, employee11);
        People people11 = new People(employee9, employee12);

        List<People> peopleList = new ArrayList<>();

        peopleList.add(people0);
        peopleList.add(people1);
        peopleList.add(people2);
        peopleList.add(people3);
        peopleList.add(people4);
        peopleList.add(people5);
        peopleList.add(people6);
        peopleList.add(people7);
        peopleList.add(people8);
        peopleList.add(people9);
        peopleList.add(people10);
        peopleList.add(people11);


        ManagerEmpLCA managerEmpLCA = new ManagerEmpLCA(peopleList);

        Employee e1 = employee1;
        Employee e2 = employee0;

        String commonManager  = managerEmpLCA.getCommonManager(e1, e2);

        System.out.println("Common Manager of "+ e1.getName()+" and "+ e2.getName()+" is "+ commonManager+".");

    }

}
