package practice_new.machine_coding.food_ordering_system.services;

import practice_new.machine_coding.food_ordering_system.ItemQuantity;
import practice_new.machine_coding.food_ordering_system.Order;
import practice_new.machine_coding.food_ordering_system.Restaurant;

import java.util.List;
import java.util.Map;

public class SelectionStrategy {

    public static Restaurant getDesiredRestaurant(List<Restaurant> restaurants, Order order) {
        Restaurant restaurant = null;
        if (order.getSelection().toString().equals("COST")) {
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
        Double lowestCost = getCostFromRestro(restaurants.get(0), order);
        Restaurant finalRestaurant = restaurants.get(0);
        for (Restaurant restaurant : restaurants) {
            Double cost = getCostFromRestro(restaurant, order);
            if (cost < lowestCost) {
                lowestCost = cost;
                finalRestaurant = restaurant;
            }
        }
        return finalRestaurant;
    }

    private static Restaurant getBestRatingRestaurant(List<Restaurant> restaurants) {
        if (restaurants.size() == 0) {
            return null;
        }
        Double bestRating = restaurants.get(0).getRating();
        Restaurant finalRestaurant = restaurants.get(0);
        for (Restaurant restaurant : restaurants) {
            Double rating = restaurant.getRating();
            if (rating > bestRating) {
                bestRating = rating;
                finalRestaurant = restaurant;
            }
        }
        return finalRestaurant;
    }

    private static Double getCostFromRestro(Restaurant restaurant, Order order) {
        Map<String, Double> itemCost = restaurant.getItemCost();
        Double totalCost = 0.0;
        for (ItemQuantity itemQuantity : order.getItemQuantities()) {
            Double cost = itemCost.get(itemQuantity.getItem());
            totalCost += cost * itemQuantity.getQuantity();
        }
        return totalCost;
    }
}
