package practice_new.machine_coding.food_ordering_system;

import practice_new.machine_coding.food_ordering_system.services.OrderService;
import practice_new.machine_coding.food_ordering_system.services.RestaurantService;

import java.util.ArrayList;
import java.util.Arrays;

public class FoodOrderingApp {
    public static void main(String[] args) {


        Item item1 = new Item("Veg Biryani", 100.0);
        Item item2 = new Item("Chicken Biryani", 150.0);
        Menu menu = new Menu(new ArrayList<>(Arrays.asList(item1, item2)));
        Restaurant restaurant1 = new Restaurant(menu, 5, 4.5);

        Item item3 = new Item("Idli", 10.0);
        Item item4 = new Item("Dosa", 50.0);
        Item item5 = new Item("Chicken Biryani", 175.0);
        Item item6 = new Item("Veg Biryani", 80.0);

        Menu menu2 = new Menu(new ArrayList<>(Arrays.asList(item3, item4, item5, item6)));
        Restaurant restaurant2 = new Restaurant(menu2, 5, 4.0);

        Item item7 = new Item("Gobi Manchurian", 150.0);
        Item item8 = new Item("Dosa", 30.0);
        Item item9 = new Item("Chicken65", 250.0);
        Item item10 = new Item("Chicken Biryani", 150.0);
        Item item11 = new Item("Idli", 15.0);

        Menu menu3 = new Menu(new ArrayList<>(Arrays.asList(item7, item8, item5, item11)));
        Restaurant restaurant3 = new Restaurant(menu3, 1, 4.9);

        RestaurantService restaurantService = new RestaurantService();
        restaurantService.onBoardRestaurant(restaurant1);
        restaurantService.onBoardRestaurant(restaurant2);
        restaurantService.onBoardRestaurant(restaurant3);
        restaurantService.addItemToRestaurant(restaurant1.getId(), item9);
        restaurantService.addItemToRestaurant(restaurant1.getId(), item9);
        restaurantService.updateItemToRestaurant(restaurant1.getId(), item10);

        OrderService orderService = new OrderService(restaurantService);



        ItemQuantity itemQuantity1 = new ItemQuantity("Idli", 3);
        ItemQuantity itemQuantity2 = new ItemQuantity("Dosa", 1);

        User user1 = new User("Ashwin");
        Order order1 = new Order(new ArrayList<>(Arrays.asList(itemQuantity1, itemQuantity2)), Selection.COST, user1);
        orderService.placeOrder(order1);

        User user2 = new User("Harish");
        Order order2 = new Order(new ArrayList<>(Arrays.asList(itemQuantity1, itemQuantity2)), Selection.COST, user2);
        orderService.placeOrder(order2);

        User user3 = new User("user3");
        ItemQuantity itemQuantity3 = new ItemQuantity("Veg Biryani", 3);

        Order order3 = new Order(new ArrayList<>(Arrays.asList(itemQuantity3)), Selection.RATING, user3);
        orderService.placeOrder(order3);

        restaurantService.updateOrderComplete(order1.id);


        Order order4 = new Order(new ArrayList<>(Arrays.asList(itemQuantity1, itemQuantity2)), Selection.COST, user2);
        orderService.placeOrder(order4);

        ItemQuantity itemQuantity4 = new ItemQuantity("Paneer", 1);
        ItemQuantity itemQuantity5 = new ItemQuantity("Idli", 1);
        Order order5 = new Order(new ArrayList<>(Arrays.asList(itemQuantity4, itemQuantity5)), Selection.COST, user2);
        orderService.placeOrder(order5);

    }


}
