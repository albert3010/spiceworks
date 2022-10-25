package practice_system_design.machine_coding.food_ordering_system;

import java.util.HashMap;
import java.util.Map;

public class Restaurant {
    String id;
    Menu menu;
    int capacity;
    Double rating;
    Map<String, Double> itemCost;

    Restaurant(Menu menu, int capacity, Double rating) {
        this.menu = menu;
        this.capacity = capacity;
        this.id = "R" + Constants.getRestaurantId();
        this.rating = rating;
        this.itemCost = new HashMap<>();
        updateItemCost(this.itemCost, menu);
    }

    static void updateItemCost(Map<String, Double> itemCost, Menu menu) {
        for(Item item: menu.getItems()){
            itemCost.put(item.itemName, item.price);
        }
    }

    public void updateItem(Item item) {
        menu.updateItem(item);
        itemCost.put(item.itemName, item.price);
    }

    public void addItem(Item item) {
        menu.addItem(item);
        itemCost.put(item.itemName, item.price);
    }

    public String getId() {
        return this.id;
    }

    public Integer getCapacity() {
        return this.capacity;
    }

    public Menu getMenu() {
        return this.menu;
    }

    public Map<String, Double> getItemCost() {
        return itemCost;
    }

    public Double getRating() {
        return this.rating;
    }

}
