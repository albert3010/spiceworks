package practice_system_design.machine_coding.coffee_machine;

public class Ingredient {
    int id;
    String name;
    private int quantity;
    private QuantityType quantityType;

    public Ingredient(String name, int quantity, QuantityType quantityType) {
        this.id = Constants.getIngredientId();
        this.name = name;
        this.quantity = quantity;
        this.quantityType = quantityType;
    }

    public Ingredient(Ingredient ingredient, int quantity) {
        this.id = ingredient.id;
        this.name = ingredient.name;
        this.quantity = quantity;
        this.quantityType = ingredient.quantityType;
    }

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }

    public void reduceQuantity(int quantity) {
        if (quantity > this.quantity) {
            System.out.println("Can't be reduced");
            return;
        }
        this.quantity -= quantity;
    }

}
