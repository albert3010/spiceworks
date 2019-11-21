package practice.company.flipkart.problems.designBuySellProducts;

/**
 * Created by OMPRAKASH on 9/30/2016.
 */
public class Product {


    private Integer productId;
    private String name;

    Product(Integer id, String name) {
        this.productId = id;
        this.name = name;
    }

    public Integer getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }
}
