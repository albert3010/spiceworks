package practice_system_design.machine_coding.coffee_machine;

import practice_system_design.machine_coding.coffee_machine.Beverage.Beverage;

import java.util.HashMap;
import java.util.List;

public class CoffeeMachine implements Machine {
    String machineName;
    int outlets;
    private HashMap<Integer, Beverage> beveragesFactory;
    private IngredientInventory ingredientInventory;

    public CoffeeMachine(String machineName, int outlets, List<Beverage> beverages, IngredientInventory ingredientInventory) {
        this.machineName = machineName;
        this.outlets = outlets;
        this.beveragesFactory = updateBeverages(beverages);
        this.ingredientInventory = ingredientInventory;
    }

    private HashMap<Integer, Beverage> updateBeverages(List<Beverage> beverages) {
        HashMap<Integer, Beverage> beveragesFactory = new HashMap<>();
        beverages.forEach(beverage -> beveragesFactory.put(beverage.id, beverage));
        return beveragesFactory;
    }

    public void addNewBeverage(Beverage beverage) {
        this.beveragesFactory.put(beverage.id, beverage);
    }

    public void displayItems() {
        System.out.println("------ Beverage available -----");
        beveragesFactory.values().forEach(beverage -> System.out.println(beverage.name));
    }

    public Ingredient getIngredient(int ingredientId) {
        Ingredient ingredient = ingredientInventory.getIngredient(ingredientId);
        if (ingredient == null) {
            System.out.println("No Beverage Found with id: " + ingredientId);
            return null;
        }
        return ingredient;
    }

    public void make(int beverageId) throws InterruptedException, Exception {
        Beverage beverage = beveragesFactory.get(beverageId);
        if (beverage == null) {
            System.out.println("Not Available");
            return;
        }
        String canMake = ingredientInventory.canMakeBeverage(beverage);
        if (canMake.equals("OK")) {
            ingredientInventory.reduceInventory(beverage);
            beverage.makeBeverage();
        } else {
            System.out.println("Can't Make");
        }
    }

    public void refill(int ingredientId, int quantity) {
        ingredientInventory.refill(ingredientId, quantity);
    }

    public void checkLowIngredientIndicator() {
        ingredientInventory.checkLowIngredientIndicator();
    }

    public void reset() {
        this.beveragesFactory = new HashMap<>();
    }

}
