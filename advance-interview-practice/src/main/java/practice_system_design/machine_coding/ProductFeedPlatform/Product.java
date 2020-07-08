package practice_system_design.machine_coding.ProductFeedPlatform;

public class Product {

    int id;
    String title;
    private double price;

    public Product(String title, double price) {
        this.id = Constants.getProductId();
        this.title = title;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }
}
