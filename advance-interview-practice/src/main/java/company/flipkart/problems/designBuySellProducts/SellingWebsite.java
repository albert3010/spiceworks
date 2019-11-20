package company.flipkart.problems.designBuySellProducts;

import org.junit.Test;

import java.util.*;

/**
 * Created by OMPRAKASH on 9/30/2016.
 */
public class SellingWebsite {

    @Test
    public void myTest() {

        Integer inventryId = 1;
        Set<Product> products = new LinkedHashSet<>();
        HashMap<User, List<Product>> purchaseList = new HashMap<>();
        Set<User> blackList = new LinkedHashSet<>();
        HashMap<Product, Set<User>> productToUsersMap = new HashMap<>();
        PriorityQueue<Purchase> purchases = new PriorityQueue<>();

        Inventary inventary = new Inventary(inventryId, products, purchaseList, blackList, productToUsersMap, purchases);


        User u1 = new User(1, "om");
        User u2 = new User(2, "harry");
        User u3 = new User(3, "chandu");
        User u4 = new User(3, "rishi");
        //add products
        Product p1 = new Product(11, "book");
        Product p2 = new Product(12, "pen");
        Product p3 = new Product(13, "mobile");
        Product p4 = new Product(14, "laptop");
        Product p5 = new Product(15, "pent");

        inventary.addProduct(p1);
        inventary.addProduct(p2);
        inventary.addProduct(p3);
        inventary.addProduct(p4);
        inventary.addProduct(p5);

        inventary.purchase(u1, p1);
        inventary.purchase(u2, p1);
        inventary.purchase(u3, p1);
        inventary.purchase(u4, p2);
        inventary.purchase(u2, p2);
        inventary.purchase(u1, p2);
        inventary.purchase(u4, p3);
//        inventary.purchase(u4, p3);

        inventary.returnProduct(u2, p1);
        inventary.blackListUser(u3);

        List<String> p = inventary.bestSellingProduct();

        System.out.println("Best selling products : "+ p+" !!!");

    }
}
