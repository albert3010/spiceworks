package practice_lld_2023.lld_2022.splitwise.model.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import practice_lld_2023.lld_2022.splitwise.Constants.Constants;

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
