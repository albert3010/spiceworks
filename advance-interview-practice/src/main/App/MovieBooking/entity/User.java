package MovieBooking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    int userId;
    String userName;
    String userEmail;
}
