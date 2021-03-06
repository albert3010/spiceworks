package practice_system_design.machine_coding.food_ordering_system.services;

import practice_system_design.machine_coding.food_ordering_system.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RestaurantService {
    Map<String, Restaurant> restaurantMap = new HashMap<>();
    Map<String, Integer> restCapacityRemaining = new HashMap<>();
    Map<Integer, String> orderToRestroMap = new HashMap<>();

    public void onBoardRestaurant(Restaurant restaurant) {
        restaurantMap.put(restaurant.getId(), restaurant);
        restCapacityRemaining.put(restaurant.getId(), restaurant.getCapacity());
    }

    public String addItemToRestaurant(String restaurantId, Item item) {
        Restaurant restaurant = restaurantMap.get(restaurantId);
        if (restaurant != null) {
            restaurant.addItem(item);
        } else {
            return "Restaurant doesn't exit";
        }
        return "Item added to Restaurant id: " + restaurantId;
    }

    public String updateItemToRestaurant(String restaurantId, Item item) {
        Restaurant restaurant = restaurantMap.get(restaurantId);
        if (restaurant != null) {
            restaurant.updateItem(item);
        } else {
            return "Restaurant doesn't exit";
        }
        return "Item updated to Restaurant id: " + restaurantId;
    }

    public List<Restaurant> getEligibleRestaurants(Order order) {
        return restaurantMap.values().stream()
                .filter(restaurant -> canRestaurantFulfilOrder(restaurant, order))
                .collect(Collectors.toList());
    }

    private boolean canRestaurantFulfilOrder(Restaurant restaurant, Order order) {
        if (restCapacityRemaining.get(restaurant.getId()) == 0)
            return false;
        List<Item> items = restaurant.getMenu().getItems();
        return order.getItemQuantities()
                .stream()
                .allMatch(itemQuantity -> items.stream()
                        .anyMatch(item -> item.getItemName().equals(itemQuantity.getItem())));
    }

    public Restaurant getBestRestaurantForOrder(Order order) {
        List<Restaurant> restaurants = getEligibleRestaurants(order);
        Restaurant restaurant = SelectionStrategy.getDesiredRestaurant(restaurants, order);
        return restaurant;
    }

    public boolean giveOrderToRestaurant(String restaurantId, Order order) {
        int orderRem = restCapacityRemaining.get(restaurantId);
        restCapacityRemaining.put(restaurantId, orderRem - 1);
        orderToRestroMap.put(order.getId(), restaurantId);
        return true;
    }

    public boolean updateOrderComplete(Integer orderId) {
        String restaurantId = orderToRestroMap.get(orderId);
        System.out.println("ORDER COMPLETED BY Restaurant: " + restaurantId + " FOR ORDER " + orderId);
        int orderRem = restCapacityRemaining.get(restaurantId);
        restCapacityRemaining.put(restaurantId, orderRem + 1);
        return true;
    }

}
