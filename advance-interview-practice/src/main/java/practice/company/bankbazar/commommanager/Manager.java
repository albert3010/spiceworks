package practice.company.bankbazar.commommanager;

/**
 * Created by OMPRAKASH on 9/30/2016.
 */
public class Manager {

   private Integer managerId;
   private String name;

    public Manager(Integer managerId, String name) {
        this.managerId = managerId;
        this.name = name;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public String getName() {
        return name;
    }
}
