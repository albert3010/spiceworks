package practice.company.gojek.entities;

/**
 * Created by OMPRAKASH on 10/2/2016.
 */
public class User {

    private String userName;
    private Integer userId;

    public User(String userName, Integer userId) {
        this.userName = userName;
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public Integer getUserId() {
        return userId;
    }
}
