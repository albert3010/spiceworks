package practice_lld.top25.lld.stock.entity;

import org.joda.time.DateTime;

public class Transaction {
    String id;
    double tradedPrice;
    int stockId;
    int userId;
    OrderType orderType;
    int quantity;
    Status status;
    DateTime date;
}
