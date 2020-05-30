package practice_new.machine_coding.food_ordering_system;

public class Item {
    String itemName;
    Double price;

    Item(String name, Double price) {
        this.itemName = name;
        this.price = price;
    }
    public String getItemName(){
        return this.itemName;
    }
    public Double getPrice(){
        return this.price;
    }
}
