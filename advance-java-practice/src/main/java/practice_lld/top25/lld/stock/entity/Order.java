package practice_lld.top25.lld.stock.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class Order {
    private int orderId;
    OrderType orderType;
    int userId;
    int stockId;
    double targetPrice;
    int quantity;
    Status status;
    Date date;
}
