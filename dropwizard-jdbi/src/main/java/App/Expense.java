package App;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public abstract class Expense {

    private int id;
    private Integer userId;
    private List<Split> splits;
    private Double amount;


    public abstract boolean validate();
}
