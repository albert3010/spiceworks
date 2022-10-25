package practice_system_design.machine_coding.ProductFeedPlatform;

import org.joda.time.DateTime;

public class Post {
    int id;
    int sellerId;
    Product product;
    DateTime postDate;

    public Post(int sellerId, Product product) {
        this.id = Constants.getPostId();
        this.sellerId = sellerId;
        this.product = product;
        this.postDate = new DateTime();
    }

    public int getId() {
        return id;
    }

    public int getSellerId() {
        return sellerId;
    }

    public DateTime getPostDate() {
        return postDate;
    }

    @Override
    public String toString() {
        System.out.println();
        return "Post{" +
                "id=" + id +
                ", sellerId=" + sellerId +
                ", product=" + product +
                ", postDate=" + postDate +
                '}';
    }
}
