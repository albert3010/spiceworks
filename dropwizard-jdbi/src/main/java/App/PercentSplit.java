package App;

import lombok.Getter;

@Getter
public class PercentSplit extends Split{
    private Integer percent;

    public PercentSplit(int userId, Integer percent){
        super(userId, 0);
        this.percent = percent;
    }

    @Override
    public void setAmount(double amount) {
        super.setAmount(amount);
    }
}
