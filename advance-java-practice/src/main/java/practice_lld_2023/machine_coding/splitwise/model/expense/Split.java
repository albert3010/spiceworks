package practice_lld_2023.machine_coding.splitwise.model.expense;

import lombok.AllArgsConstructor;
import lombok.Getter;
import practice_lld_2023.machine_coding.splitwise.Constants.SplitType;

import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
public abstract class Split {
    private SplitType splitType;
    private List<Integer> users;
    private Double totalAmount;

    public abstract Map<Integer, Double> getOweAmount() throws Exception;
    public abstract boolean validate();

}
