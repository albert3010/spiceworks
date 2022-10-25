package App;


import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class Split {
    private int userId;
    private double amount;

    public Split(int userId, double amount) {
        this.userId = userId;
        this.amount = amount;
    }

    public Split(int userId) {
        this.userId = userId;
        this.amount =0;
    }
}
