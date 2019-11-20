package company.flipkart.problems.designBuySellProducts;

import java.util.*;

import static javax.swing.UIManager.get;

/**
 * Created by OMPRAKASH on 9/30/2016.
 */
public class Inventary implements BaseInventry {

    private Integer inventryId;
    private Set<Product> products;
    private HashMap<User, List<Product>> purchaseList;
    private Set<User> blackList;
    private HashMap<Product, Set<User>> productToUsersMap;
    private PriorityQueue<Purchase> purchases;


    public Inventary(Integer inventryId, Set<Product> products, HashMap<User, List<Product>> purchaseList, Set<User> blackList, HashMap<Product, Set<User>> productToUsersMap, PriorityQueue<Purchase> purchases) {
        this.inventryId = inventryId;
        this.products = products;
        this.purchaseList = purchaseList;
        this.blackList = blackList;
        this.productToUsersMap = productToUsersMap;
        this.purchases = purchases;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void addProduct(Product p) {

        products.add(p);

    }

    public void purchase(User u, Product p){

        if(!products.contains(p)){
            System.out.println("Product is not in the inventry");
            return;
        }
       List<Product> userProducts =  purchaseList.get(u);

        if( userProducts==null || userProducts.isEmpty())
        {
            userProducts = new ArrayList<>();
            userProducts.add(p);
            purchaseList.put(u,userProducts);
        } else {
            userProducts.add(p);
        }

        Set<User> users = productToUsersMap.get(p);

        if (users == null || users.isEmpty()) {
            users = new LinkedHashSet<>();
            users.add(u);
            productToUsersMap.put(p, users);

        } else {
            users.add(u);

        }
    }

   public void returnProduct(User u, Product p){

    List<Product> userProducts =  purchaseList.get(u);

       if(userProducts!=null && userProducts.contains(p)) {
           userProducts.remove(p);
           productToUsersMap.get(p).remove(u);
       }else {
           System.out.println("You have already return this product");
       }

   }

   public void blackListUser(User u){
       List<Product> product = purchaseList.get(u);
       for(Product p : product){
           productToUsersMap.get(p).remove(u);
       }
       purchaseList.get(u).clear();
       blackList.add(u);
   }
   public List<String> bestSellingProduct(){

       Set<Product> products = productToUsersMap.keySet();
       List<String> bestProduct= new ArrayList<>();
       int max=0,tmp;
       for(Product p :products ){
           tmp = productToUsersMap.get(p).size();

           if(tmp>max){
               max=tmp;
           }

       }
       for(Product p :products ){
           tmp = productToUsersMap.get(p).size();

           if(tmp==max){
               bestProduct.add(p.getName());
               max=tmp;
           }

       }

       return bestProduct;
   }

    @Override
    public String toString() {
       StringBuilder product = new StringBuilder();

        products.forEach(p -> product.append(p.getName() + ", "));

        return product.toString();
    }
}





