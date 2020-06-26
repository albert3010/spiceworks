package practice_system_design.machine_coding.coffee_machine.Beverage;

import practice_system_design.machine_coding.coffee_machine.Ingredient;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Coffee extends Beverage {

    public Coffee(String name, List<Ingredient> ingredients) {
        super(name, ingredients);
    }

    public void makeBeverage() {
        System.out.println("---Making " + name + "---");
        System.out.println("---" + name + " Ready---");
    }
}
