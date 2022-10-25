package practice_system_design.machine_coding.food_ordering_system.services;

import practice_system_design.machine_coding.food_ordering_system.Order;
import practice_system_design.machine_coding.food_ordering_system.Restaurant;

public class OrderService {
    RestaurantService restaurantService;

    public OrderService(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    public void placeOrder(Order order) {
        Restaurant restaurant = restaurantService.getBestRestaurantForOrder(order);
        if (restaurant == null) {
            System.out.println("ORDER Failed: No Restaurant Found for given ORDER");
        } else if (restaurantService.giveOrderToRestaurant(restaurant.getId(), order)) {
            System.out.println("Order assigned to " + restaurant.getId());
        }
    }
}
