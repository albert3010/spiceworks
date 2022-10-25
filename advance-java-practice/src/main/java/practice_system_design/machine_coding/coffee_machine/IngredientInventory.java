package practice_system_design.machine_coding.coffee_machine;

import practice_system_design.machine_coding.coffee_machine.Beverage.Beverage;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class IngredientInventory {
    int threshold = 50;
    HashMap<Integer, Ingredient> ingredientData;

    public IngredientInventory(List<Ingredient> ingredients) {
        this.ingredientData = updateIngredients(ingredients);
    }

    private HashMap<Integer, Ingredient> updateIngredients(List<Ingredient> ingredients) {
        HashMap<Integer, Ingredient> ingredientsData = new HashMap<>();
        ingredients.forEach(ingredient -> ingredientsData.put(ingredient.id, ingredient));
        return ingredientsData;
    }

    public String canMakeBeverage(Beverage beverage) throws Exception {
        // Need to refactor this
        AtomicReference<String> msg = new AtomicReference<>("");
        boolean check = beverage.getIngredients().stream()
                .allMatch(e -> {
                    Ingredient ingredient = ingredientData.get(e.getId());
                    if (ingredient == null) {
                        msg.set(beverage.name + " cannot be prepared because " + e.name + " is not available");
                        return false;
                    }
                    if (ingredient.getQuantity() < e.getQuantity()) {
                        msg.set(beverage.name + " cannot be prepared because item " + e.name + " is not sufficient");
                        return false;
                    }
                    return true;
                });

        if (check) {
            return "OK";
        } else {
            throw new Exception(msg.get());
        }
    }

    void refill(int ingredientId, int quantity) {
        Ingredient ingredient = ingredientData.get(ingredientId);
        if (ingredient == null) {
            System.out.println("Didn't have existing inventory");
            return;
        }
        System.out.println("--Refilling ---" + ingredient.name + " with " + quantity);
        ingredient.addQuantity(quantity);
        ingredientData.put(ingredientId, ingredient);
    }

    void reduceInventoryById(int ingredientId, int quantity) {
        Ingredient ingredient = ingredientData.get(ingredientId);
        if (ingredient == null) {
            System.out.println("Didn't have existing inventory");
            return;
        }
        ingredient.reduceQuantity(quantity);
        ingredientData.put(ingredientId, ingredient);
    }

    void reduceInventory(Beverage beverage) {
        beverage.getIngredients().forEach(e -> reduceInventoryById(e.id, e.getQuantity()));
    }

    Ingredient getIngredient(int ingredientId) {
        Ingredient ingredient = ingredientData.get(ingredientId);
        if (ingredient == null) {
            System.out.println("No Ingredient Found with id: " + ingredientId);
            return null;
        }
        return ingredient;
    }

    public void checkLowIngredientIndicator() {
        ingredientData.entrySet().stream()
                .filter(e -> e.getValue().getQuantity() <= threshold)
                .forEach(e ->
                        System.out.println("Need to refill Ingredient " + e.getValue().name + " having Id: " + e.getKey() + " val: " + e.getValue().getQuantity()));
    }

}
