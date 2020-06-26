package practice_system_design.machine_coding.coffee_machine;

import practice_system_design.machine_coding.coffee_machine.Beverage.Beverage;

public interface Machine {
    void addNewBeverage(Beverage beverage);

    void make(int id) throws InterruptedException, Exception;

    void refill(int ingredientId, int quantity);

    void displayItems();
}
