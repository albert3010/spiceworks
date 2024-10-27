package practice_lld.top25.lld.practice.foodDelivery;


import practice_lld.top25.lld.practice.foodDelivery.dao.OrderDao;
import practice_lld.top25.lld.practice.foodDelivery.dao.RestaurentDao;
import practice_lld.top25.lld.practice.foodDelivery.entity.*;
import practice_lld.top25.lld.practice.foodDelivery.entity.user.Customer;
import practice_lld.top25.lld.practice.foodDelivery.entity.user.DeliveryPartner;

import java.util.List;
import java.util.UUID;

public class FoodDeliveryService {
    private static FoodDeliveryService INSTANCE;
    private OrderDao orderDao;
    private RestaurentDao restaurentDao;


    public static synchronized FoodDeliveryService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FoodDeliveryService();
        }
        return INSTANCE;
    }

    void placeOrder(Customer customer, String restaurantId, List<OrderItem> items) {
        double totalAmount = getTotalAmount(items);
        Restaurent restaurent = restaurentDao.getRestaurent(restaurantId);
        Order order = new Order(getOrderId(), customer, restaurent, items, totalAmount, PaymentStatus.SUCCESS, OrderStatus.PENDING);
        orderDao.addOrder(order);
    }

    void updateStatus(String orderId, OrderStatus orderStatus){
        orderDao.updateSatus(orderId, orderStatus);
    }

    DeliveryPartner getDeliveryPartner(){
        return new DeliveryPartner("1","", "");
    }

    void assignPartnerToOrder(Order order, DeliveryPartner deliveryPartner){

    }

    public double getTotalAmount(List<OrderItem> orderItems) {
        double totalPrice = 0.0;
        for (OrderItem orderItem : orderItems) {
            totalPrice += orderItem.getFoodItem().getPrice() * orderItem.getQuantity();
        }
        return totalPrice;
    }

    private String getOrderId() {
        return UUID.randomUUID().toString().substring(0, 4);
    }
}
