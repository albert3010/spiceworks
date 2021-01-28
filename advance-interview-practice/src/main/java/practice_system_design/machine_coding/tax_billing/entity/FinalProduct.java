package practice_system_design.machine_coding.tax_billing.entity;


public class FinalProduct {
    String item;
    double basePrice;
    double finalPrice;

    public FinalProduct(String item, double basePrice, double finalPrice) {
        this.item = item;
        this.basePrice = basePrice;
        this.finalPrice = finalPrice;
    }

}
