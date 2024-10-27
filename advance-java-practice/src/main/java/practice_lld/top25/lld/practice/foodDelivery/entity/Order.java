package practice_lld.top25.lld.practice.foodDelivery.entity;

import lombok.Getter;
import practice_lld.top25.lld.practice.foodDelivery.entity.user.Customer;
import practice_lld.top25.lld.practice.foodDelivery.entity.user.DeliveryPartner;

import java.util.List;

@Getter
public class Order {
    String orderId;
    Customer orderedBy;
    Restaurent restaurent;
    List<OrderItem> orderItems;
    double totalAmount;
    PaymentStatus paymentStatus;
    OrderStatus orderStatus;
    DeliveryPartner deliveryPartner;

    public Order(String orderId, Customer orderedBy, Restaurent restaurent, List<OrderItem> orderItems, double totalAmount, PaymentStatus paymentStatus, OrderStatus orderStatus) {
        this.orderId = orderId;
        this.orderedBy = orderedBy;
        this.restaurent = restaurent;
        this.orderItems = orderItems;
        this.totalAmount = totalAmount;
        this.paymentStatus = paymentStatus;
        this.orderStatus = orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
