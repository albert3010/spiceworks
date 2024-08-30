package practice_lld.top25.lld.splitwise.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import practice_lld.top25.lld.splitwise.Constants;

@AllArgsConstructor
@Getter
public class User {
    int userId;
    String name;
    String emailId;
    String phoneNo;

    public User(String name, String emailId, String phoneNo) {
        this.name = name;
        this.emailId = emailId;
        this.phoneNo = phoneNo;
        this.userId = Constants.getUserId();
    }
}
// use record java 11
