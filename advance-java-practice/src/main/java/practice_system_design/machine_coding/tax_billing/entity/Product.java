package practice_system_design.machine_coding.tax_billing.entity;


public class Product {
    String item;
    double basePrice;
    long discount;

    public Product(String item, double basePrice, long discount) {
        this.item = item;
        this.basePrice = basePrice;
        this.discount = discount;
    }

    public Product(String item, double basePrice) {
        this.item = item;
        this.basePrice = basePrice;
    }

    public String getItem() {
        return item;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public Long getDiscount() {
        return discount;
    }
}
