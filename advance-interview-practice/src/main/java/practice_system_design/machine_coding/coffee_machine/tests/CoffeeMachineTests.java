package practice_system_design.machine_coding.coffee_machine.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import practice_system_design.machine_coding.coffee_machine.Beverage.Beverage;
import practice_system_design.machine_coding.coffee_machine.Beverage.Coffee;
import practice_system_design.machine_coding.coffee_machine.Beverage.HotTea;
import practice_system_design.machine_coding.coffee_machine.CoffeeMachine;
import practice_system_design.machine_coding.coffee_machine.Ingredient;
import practice_system_design.machine_coding.coffee_machine.IngredientInventory;
import practice_system_design.machine_coding.coffee_machine.QuantityType;

import java.sql.SQLOutput;
import java.util.*;

public class CoffeeMachineTests {
    static CoffeeMachine coffeeMachine;
    static List<Beverage> beverages = new ArrayList<>();


    @BeforeClass
    public static void setupCoffeeMachine() {
        System.out.println("----- Start machine  ----");
        Ingredient hot_water = new Ingredient("hot_water", 500, QuantityType.ML);
        Ingredient hot_milk = new Ingredient("hot_milk", 500, QuantityType.ML);
        Ingredient ginger_syrup = new Ingredient("ginger_syrup", 100, QuantityType.ML);
        Ingredient sugar_syrup = new Ingredient("sugar_syrup", 100, QuantityType.ML);
        Ingredient tea_leaves_syrup = new Ingredient("tea_leaves_syrup", 100, QuantityType.ML);
        Ingredient green_mixture = new Ingredient("green_mixture", 0, QuantityType.ML);
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(hot_water);
        ingredients.add(hot_milk);
        ingredients.add(ginger_syrup);
        ingredients.add(sugar_syrup);
        ingredients.add(tea_leaves_syrup);
        IngredientInventory ingredientInventory = new IngredientInventory(ingredients);

        List<Ingredient> CoffeeIngredients = new ArrayList<>();
        CoffeeIngredients.add(new Ingredient(hot_water, 100));
        CoffeeIngredients.add(new Ingredient(ginger_syrup, 30));
        CoffeeIngredients.add(new Ingredient(hot_milk, 400));
        CoffeeIngredients.add(new Ingredient(sugar_syrup, 50));
        CoffeeIngredients.add(new Ingredient(tea_leaves_syrup, 30));
        Beverage coffee = new Coffee("hot_coffee", CoffeeIngredients);

        List<Ingredient> hotTeaIngredients = new ArrayList<>();
        hotTeaIngredients.add(new Ingredient(hot_water, 200));
        hotTeaIngredients.add(new Ingredient(hot_milk, 100));
        hotTeaIngredients.add(new Ingredient(ginger_syrup, 10));
        hotTeaIngredients.add(new Ingredient(sugar_syrup, 10));
        hotTeaIngredients.add(new Ingredient(tea_leaves_syrup, 30));
        Beverage hotTea = new HotTea("hot_tea", hotTeaIngredients);

        List<Ingredient> blackTeaIngredients = new ArrayList<>();
        blackTeaIngredients.add(new Ingredient(hot_water, 300));
        blackTeaIngredients.add(new Ingredient(ginger_syrup, 30));
        blackTeaIngredients.add(new Ingredient(sugar_syrup, 50));
        blackTeaIngredients.add(new Ingredient(tea_leaves_syrup, 30));
        Beverage blackTea = new HotTea("black_tea", blackTeaIngredients);

        List<Ingredient> greenTeaIngredients = new ArrayList<>();
        greenTeaIngredients.add(new Ingredient(hot_water, 100));
        greenTeaIngredients.add(new Ingredient(ginger_syrup, 30));
        greenTeaIngredients.add(new Ingredient(sugar_syrup, 50));
        greenTeaIngredients.add(new Ingredient(green_mixture, 30));
        Beverage greenTea = new HotTea("green_tea", greenTeaIngredients);

        beverages.add(coffee);
        beverages.add(hotTea);
        beverages.add(blackTea);
        beverages.add(greenTea);

        coffeeMachine = new CoffeeMachine("CoffeeMachine", 3, beverages, ingredientInventory);
        coffeeMachine.displayItems();
        System.out.println();
    }

    @Test
    public void OrderCoffeeTest() throws Exception {

        coffeeMachine.make(beverages.get(0).id);
        System.out.println("-------------------");
    }
    @Test
    public void OrderBlackTeaTest() throws Exception {
        coffeeMachine.make(beverages.get(2).id);
        System.out.println("-------------------");
    }

    @Test
    public void OrderGreenTestItemNotSufficientTest() throws InterruptedException, Exception {
        try {
            coffeeMachine.make(beverages.get(0).id);
        } catch(Exception e) {
            Assert.assertEquals(e.getMessage(), "hot_coffee cannot be prepared because item hot_milk is not sufficient");
        }

        System.out.println("-------------------");
    }
    @Test
    public void checkLowIngredientIndicatorTest() throws InterruptedException {
        coffeeMachine.checkLowIngredientIndicator();
        System.out.println("-------------------");
    }
    @Test
    public void refillCoffeeMachineTest() throws InterruptedException {
        coffeeMachine.refill(4, 200); // sugar_syrup
        coffeeMachine.refill(2, 500); // hot milk
        coffeeMachine.refill(1, 500); // hot milk
//        Assert.assertEquals(coffeeMachine.getIngredient(4).getQuantity(), 200);
//        Assert.assertEquals(coffeeMachine.getIngredient(2).getQuantity(), 600);
//        Assert.assertEquals(coffeeMachine.getIngredient(1).getQuantity(), 600);
        System.out.println("-------------------");
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        q.add(2);
        q.add(3);
//        System.out.println(q.poll());
        Deque<Integer> deque = new LinkedList<>();
        deque.add(1);
        deque.add(2);
        deque.add(3);

        System.out.println(deque.poll());
        System.out.println(deque.getLast());

    }

    @Test
    public void OrderHotCoffeeAfterRefillHotMilkTest() throws Exception {
        coffeeMachine.make(beverages.get(0).id);
        System.out.println("-------------------");
    }

    @Test
    public void OrderGreenTeaMixtureNotAvailableTest() throws Exception {
        try {
            coffeeMachine.make(beverages.get(3).id);
        } catch(Exception e) {
            Assert.assertEquals(e.getMessage(), "green_tea cannot be prepared because item ginger_syrup is not sufficient");
        }
        System.out.println("-------------------");
    }
}
