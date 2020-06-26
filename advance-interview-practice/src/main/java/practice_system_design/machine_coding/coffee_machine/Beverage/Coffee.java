package practice_system_design.machine_coding.coffee_machine;

import java.util.List;

public abstract class Beverage {
    int id;
    String name;
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

    public void makeBeverage() {
//        System.out.println("---Making " + name + "---");
//        Thread.sleep(100);
//        System.out.println("---" + name + " Ready---");
    }
}
