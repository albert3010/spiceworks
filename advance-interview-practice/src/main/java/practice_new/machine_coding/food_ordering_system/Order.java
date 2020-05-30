package practice_new.machine_coding.food_ordering_system;

import java.util.List;

public class Order {
    int id;
    List<ItemQuantity> itemQuantities;
    Selection selection;
    User user;

    Order(List<ItemQuantity> itemQuantities, Selection selection, User user) {
        this.itemQuantities = itemQuantities;
        this.selection = selection;
        this.user = user;
        this.id = Constants.getOrderId();
    }

    public List<ItemQuantity> getItemQuantities() {
        return this.itemQuantities;
    }

    public Selection getSelection() {
        return this.selection;
    }

    public int getId() {
        return this.id;
    }
}
