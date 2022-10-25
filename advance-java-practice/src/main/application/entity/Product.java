package entity;

import lombok.Data;

@Data
public class Product {
    int productId;
    String name;
    double price;
    Address address;
    int inventory;

    public Product(String name, double price, Address address, int inventory){
        this.address = address;
        this.name = name;
        this.price = price;
        this.inventory = inventory;
        this.productId = IdCounters.getProductId();

    }

}
