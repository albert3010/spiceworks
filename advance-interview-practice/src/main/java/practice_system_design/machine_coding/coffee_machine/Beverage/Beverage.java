package practice_system_design.machine_coding.coffee_machine.Beverage;

import practice_system_design.machine_coding.coffee_machine.Constants;
import practice_system_design.machine_coding.coffee_machine.Ingredient;

import java.util.List;

public abstract class Beverage {
    public int id;
    public String name;
    private List<Ingredient> ingredients;

    public Beverage(String name, List<Ingredient> ingredients) {
        this.id = Constants.getBeverageId();
        this.name = name;
        this.ingredients = ingredients;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void addNewIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }
    public abstract void makeBeverage() throws InterruptedException;
}
