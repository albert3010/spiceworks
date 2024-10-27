package practice_lld.top25.lld.practice.foodDelivery.dao;

import practice_lld.top25.lld.practice.foodDelivery.entity.Order;
import practice_lld.top25.lld.practice.foodDelivery.entity.OrderStatus;

public interface IOrder {

    void addOrder(Order order);

    void updateSatus(String orderId, OrderStatus orderStatus);
}
