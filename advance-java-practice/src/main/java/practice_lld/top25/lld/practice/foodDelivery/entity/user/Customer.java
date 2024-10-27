package practice_lld.top25.lld.practice.foodDelivery.entity.user;

import practice_lld.top25.lld.practice.foodDelivery.entity.FoodType;

public class Customer extends User {
    FoodType foodPreference;

    public Customer(String id, String name, String email) {
        super(id, name, email);
        this.foodPreference = FoodType.VEG;
    }
}
