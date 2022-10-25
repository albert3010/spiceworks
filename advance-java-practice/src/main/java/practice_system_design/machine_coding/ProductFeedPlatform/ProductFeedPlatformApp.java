package practice_system_design.machine_coding.ProductFeedPlatform;

import practice_system_design.machine_coding.ProductFeedPlatform.services.ProductFeedService;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ProductFeedPlatformApp {

    @Test
    public void FeedTest() {
        Seller s1 = new Seller("Tony", 4);
        Seller s2 = new Seller("Thor", 3);
        List<Seller> sellerList = new ArrayList<>();
        sellerList.add(s1);
        sellerList.add(s2);
        Product p1 = new Product("Macbook pro", 200000);
        Product p2 = new Product("One Plus 7 pro phone", 50000);
        Product p3 = new Product("Macbook pro", 180000);
        Product p4 = new Product("One Plus 7 pro phone", 60000);

        Customer customer1 = new Customer("Thanos");

        ProductFeedService productFeedService = new ProductFeedService(sellerList);
        productFeedService.addCustomer(customer1);

        Post post1 = new Post(s1.id, p1);
        productFeedService.addPost(s1.id, post1);

        Post post2 = new Post(s1.id, p2);
        productFeedService.addPost(s1.id, post2);

        Post post3 = new Post(s2.id, p3);
        productFeedService.addPost(s2.id, post3);

        Post post4 = new Post(s2.id, p4);
        productFeedService.addPost(s2.id, post4);

        List<Seller> sellers = productFeedService.getAllSeller();
        System.out.println(sellers.toString());

        productFeedService.subscribeToSeller(customer1.id, s1.id);
        productFeedService.subscribeToSeller(customer1.id, s2.id);


        List<Post> posts = productFeedService.getAllPostOfUser(customer1.id, SelectionType.LATEST);
        System.out.println(posts.toString());

        productFeedService.deletePost(s2.id, post3.id);

        posts = productFeedService.getAllPostOfUser(customer1.id, SelectionType.LATEST);
        System.out.println(posts.toString());

        productFeedService.unSubscribeToSeller(customer1.id, s1.id);

        posts = productFeedService.getAllPostOfUser(customer1.id, SelectionType.LATEST);
        System.out.println(posts.toString());

        productFeedService.deleteSeller(s2.id);

        posts = productFeedService.getAllPostOfUser(customer1.id, SelectionType.LATEST);
        System.out.println(posts.toString());

        sellers = productFeedService.getAllSeller();
        System.out.println(sellers.toString());


    }

    void printPosts(List<Post> posts) {
        for (Post post : posts) {
            System.out.println("------------");
            System.out.println(post.product);
            System.out.println(post.product.getPrice());
            System.out.println("------------");
        }
    }


}
