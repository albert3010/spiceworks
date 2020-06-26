package practice_system_design.machine_coding.coffee_machine;

import java.util.concurrent.atomic.AtomicInteger;

public class Constants {

    private static final AtomicInteger beverageId = new AtomicInteger(0);
    private static final AtomicInteger ingredientId = new AtomicInteger(0);

    public static Integer getBeverageId() {
        return beverageId.incrementAndGet();
    }

    public static Integer getIngredientId() {
        return ingredientId.incrementAndGet();
    }
}
