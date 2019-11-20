package company.flipkart.problems.designBuySellProducts;

/**
 * Created by OMPRAKASH on 9/30/2016.
 */

public class User {

   private Integer userId;
   private String name;

    User(Integer userId, String name){
        this.userId = userId;
        this.name = name;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }
}
