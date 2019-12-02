package algorithms.coffeeMachine.items;

import algorithms.coffeeMachine.type.Type;

public interface ItemX {

    void prepareItem(Type type);

    boolean isItemAvailable(Type type);

}
