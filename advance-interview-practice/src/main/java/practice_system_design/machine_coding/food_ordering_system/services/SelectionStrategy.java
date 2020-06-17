package practice_system_design.machine_coding.food_ordering_system.services;

import practice_system_design.machine_coding.food_ordering_system.Order;
import practice_system_design.machine_coding.food_ordering_system.Restaurant;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class SelectionStrategy {

    public static Restaurant getDesiredRestaurant(List<Restaurant> restaurants, Order order) {
        Restaurant restaurant = null;
        if (order.getSelection().equals("COST")) {
            restaurant = getLowestCostRestaurant(restaurants, order);
        } else {
            restaurant = getBestRatingRestaurant(restaurants);
        }
        return restaurant;
    }

    private static Restaurant getLowestCostRestaurant(List<Restaurant> restaurants, Order order) {
        if (restaurants.size() == 0) {
            return null;
        }
        return Collections.min(restaurants, Comparator.comparing(restaurant -> getCostFromRestro(restaurant, order)));
    }

    private static Restaurant getBestRatingRestaurant(List<Restaurant> restaurants) {
        if (restaurants.size() == 0) {
            return null;
        }
        return Collections.max(restaurants, Comparator.comparing(Restaurant::getRating));
    }

    private static Double getCostFromRestro(Restaurant restaurant, Order order) {
        Map<String, Double> itemCost = restaurant.getItemCost();
        return order.getItemQuantities().stream()
                .mapToDouble(e -> itemCost.get(e.getItem()) * e.getQuantity()).sum();
    }
}
