package algorithms.coffeeMachine;

import algorithms.coffeeMachine.items.ItemX;
import algorithms.coffeeMachine.type.Type;

import java.util.HashMap;

public class ItemInventory {

    static HashMap<String, HashMap<Type, Integer>> ItemStore = new HashMap<>();

    public static boolean getAvailableItem(ItemX item, Type type){

        int checkAvailable  = ItemStore.get(item).get(type);
        if(checkAvailable>0){
            return true;
        }
        return false;

    }
    public static HashMap<String, HashMap<Type, Integer>> getAllItem(){
        return ItemStore;
    }

    public static void reduceItem(String item, Type type){

        int count = ItemStore.get(item).get(type);
        if(count>0){
            ItemStore.get(item).put(type, count-1);
        }
    }


}
