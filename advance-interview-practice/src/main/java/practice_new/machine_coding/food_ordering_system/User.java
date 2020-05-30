package practice_new.machine_coding.food_ordering_system;

public class User {
    int userId;
    String name;

    User(String name) {
        this.userId = Constants.getUserId();
        this.name = name;
    }
}
