package practice_new.machine_coding.food_ordering_system;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    int menuId;
    private List<Item> items = new ArrayList<>();

    Menu(List<Item> items) {
        this.menuId = Constants.getMenuId();
        this.items = items;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void updateItem(Item item) {
        for (Item item_ : items) {
            if (item_.itemName.equals(item.itemName)) {
                item_.price = item.price;
                break;
            }
        }
    }
    public List<Item> getItems(){
        return this.items;
    }

}
