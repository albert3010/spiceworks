package practice_system_design.machine_coding.coffee_machine.Beverage;

import practice_system_design.machine_coding.coffee_machine.Ingredient;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class HotTea extends Beverage {

    public HotTea(String name, List<Ingredient> ingredients) {
        super(name, ingredients);
    }

    public void makeBeverage() throws InterruptedException {
        // Will implement own preparation method
        System.out.println("---Start Making " + name + "---");
        System.out.println("--- Preparing ----- ");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("---" + name + " Ready---");
    }
}
