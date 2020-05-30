package practice_new.machine_coding.food_ordering_system;

public class ItemQuantity {
    String item;
    int quantity;

    ItemQuantity(String item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public String getItem() {
        return this.item;
    }

    public int getQuantity() {
        return this.quantity;
    }
}
