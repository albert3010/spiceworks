package practice_lld.top25.lld.practice.foodDelivery.entity;

import lombok.Value;

@Value
public class FoodItem {
    String name;
    String description;
    double price;
    FoodType foodType;

}
