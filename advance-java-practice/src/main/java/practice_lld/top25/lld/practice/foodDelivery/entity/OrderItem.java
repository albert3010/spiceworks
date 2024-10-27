package practice_lld.top25.lld.practice.foodDelivery.entity;


import lombok.Value;

@Value
public class OrderItem {

    FoodItem foodItem;
    int quantity;

}
