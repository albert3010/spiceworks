package practice_lld_2023.machine_coding.splitwise.model.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import practice_lld_2023.machine_coding.splitwise.Constants.Constants;

@AllArgsConstructor
@Getter
public class User {
    Integer userId;
    String name;
    String email;
    Integer phoneNo;

    public User(String name, String email, Integer phoneNo) {
        this.userId = Constants.getUserId();
        this.name = name;
        this.email = email;
        this.phoneNo = phoneNo;
    }
}
