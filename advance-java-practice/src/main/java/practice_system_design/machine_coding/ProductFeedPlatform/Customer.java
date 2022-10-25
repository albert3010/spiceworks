package practice_system_design.machine_coding.ProductFeedPlatform;

public class Customer {

    int id;
    String name;
    public Customer(String name) {
        this.id = Constants.getUserId();
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
