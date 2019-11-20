package company.flipkart.problems.designBuySellProducts;

/**
 * Created by OMPRAKASH on 9/30/2016.
 */
public class Purchase implements Comparable<Purchase> {

    private Product product;
    private Integer count;

    public Product getProduct() {
        return product;
    }

    public Integer getUsers() {
        return count;
    }

    @Override
    public int compareTo(Purchase o)
    {
        return Integer.compare(this.count, o.count);
    }


}
