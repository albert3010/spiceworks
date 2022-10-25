package practice_system_design.machine_coding.ProductFeedPlatform;

import java.util.ArrayList;
import java.util.List;

public class Seller {
    int id;
    String name;
    List<Post> posts;
    double rating;

    public Seller(String name, int rating) {
        this.id = Constants.getSellerId();
        this.name = name;
        this.rating = rating;
        this.posts = new ArrayList<>();
    }

    public List<Post> getPosts() {
        return this.posts;
    }

    public void addPosts(Post post) {
        this.posts.add(post);
    }

    public void removePost(Post post) {
        posts.remove(post);
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        System.out.println();
        return "Seller{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", posts=" + posts +
                ", rating=" + rating +
                '}';
    }
}
