package entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Order {
    int orderId;
    Product product;
    Buyer buyer;
    int quantity;

    public Order(Product product , Buyer buyer, int quantity){
        this.product = product;
        this.buyer = buyer;
        this.quantity = quantity;
        this.orderId = IdCounters.getOrderId();
    }

}
