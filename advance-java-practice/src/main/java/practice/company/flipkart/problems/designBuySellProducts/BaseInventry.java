package practice.company.flipkart.problems.designBuySellProducts;

/**
 * Created by OMPRAKASH on 9/30/2016.
 */
public interface BaseInventry {

    void addProduct(Product p);

    void purchase(User u, Product p);

    void returnProduct(User u, Product p);

    void blackListUser(User u);
}
