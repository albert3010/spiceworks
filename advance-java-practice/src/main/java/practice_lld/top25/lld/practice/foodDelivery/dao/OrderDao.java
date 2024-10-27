package practice_lld.top25.lld.practice.foodDelivery.dao;

import practice_lld.top25.lld.practice.foodDelivery.entity.Order;
import practice_lld.top25.lld.practice.foodDelivery.entity.OrderStatus;

import java.util.Map;

public class OrderDao implements IOrder{
    Map<Integer, Order> orderMap;

    @Override
    public void addOrder(Order order) {

    }

    @Override
    public void updateSatus(String orderId, OrderStatus orderStatus) {

    }

    public Order getOrder(String id) {
        return orderMap.get(id);
    }
}
