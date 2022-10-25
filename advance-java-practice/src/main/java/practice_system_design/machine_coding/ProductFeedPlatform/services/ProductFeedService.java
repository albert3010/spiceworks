package practice_system_design.machine_coding.ProductFeedPlatform.services;

import practice_system_design.machine_coding.ProductFeedPlatform.Customer;
import practice_system_design.machine_coding.ProductFeedPlatform.Post;
import practice_system_design.machine_coding.ProductFeedPlatform.SelectionType;
import practice_system_design.machine_coding.ProductFeedPlatform.Seller;


import java.util.*;


public class ProductFeedService {

    HashMap<Integer, Seller> sellerHashMap = new HashMap<>();
    HashMap<Integer, Post> postHashMap = new HashMap<>();
    HashMap<Integer, Set<Integer>> userSellersSub = new HashMap<>();
    HashMap<Integer, Customer> customerHashMap = new HashMap<>();

    public ProductFeedService(List<Seller> sellers) {
        updateSeller(sellers);
    }

    public void addCustomer(Customer customer) {
        customerHashMap.put(customer.getId(), customer);
    }

    void updateSeller(List<Seller> sellers) {
        sellers.stream().forEach(seller -> this.sellerHashMap.put(seller.getId(), seller));
    }

    void addSeller(Seller seller) {
        sellerHashMap.putIfAbsent(seller.getId(), seller);
    }

    public boolean addPost(int sellerId, Post post) {
        if (sellerHashMap.get(sellerId) != null) {
            Seller seller = sellerHashMap.get(sellerId);
            seller.addPosts(post);
            sellerHashMap.putIfAbsent(sellerId, seller);
            postHashMap.putIfAbsent(post.getId(), post);
            return true;
        }
        return false;
    }

    public boolean deletePost(int sellerId, int postId) {
        if (sellerHashMap.get(sellerId) != null) {
            Seller seller = sellerHashMap.get(sellerId);
            Post deletePost = null;
            List<Post> posts = seller.getPosts();
            for (Post post : posts) {
                if (post.getId() == postId) {
                    deletePost = post;
                }
            }
            if (deletePost != null) {
                posts.remove(deletePost);
                sellerHashMap.put(sellerId, seller);
                return true;
            }
        }
        return false;
    }

    public List<Seller> getAllSeller() {
        List<Seller> allSellers = new ArrayList<>();
        for (int sellerId : sellerHashMap.keySet()) {
            allSellers.add(sellerHashMap.get(sellerId));
        }
        return allSellers;
    }

    public boolean deleteSeller(int sellerId) {
        if (sellerHashMap.get(sellerId) != null) {
            sellerHashMap.remove(sellerId);
            // update user subscribe
            updateCustomerSub(sellerId);
            return true;
        }
        return false;
    }

    void updateCustomerSub(int sellerId) {
        for (int userId : userSellersSub.keySet()) {
            unSubscribeToSeller(userId, sellerId);
        }
    }

    public void subscribeToSeller(int userId, int sellerId) {
        userSellersSub.putIfAbsent(userId, new HashSet<>());
        Set<Integer> sellersId = userSellersSub.get(userId);
        sellersId.add(sellerId);
        userSellersSub.put(userId, sellersId);
    }

    public boolean unSubscribeToSeller(int userId, int sellerId) {
        if (userSellersSub.get(userId) != null) {
            Set<Integer> sellerIds = userSellersSub.get(userId);
            if (sellerIds.contains(sellerId)) {
                sellerIds.remove(sellerId);
                userSellersSub.put(userId, sellerIds);
                return true;
            }
        }
        return false;
    }

    public List<Post> getAllPostOfUser(int userId, SelectionType selectionType) {
        Set<Integer> sellersSubByUser = userSellersSub.get(userId);
        List<Post> allPost = new ArrayList<>();

        for (int sellerId : sellersSubByUser) {
            if (sellerHashMap.get(sellerId) != null) {
                Seller seller = sellerHashMap.get(sellerId);
                allPost.addAll(seller.getPosts());
            }
        }
//        sortByRanking(allPost, sellerRatingInfo);
        sortByDate(allPost);
        return allPost;

    }

    List<Post> arrange(List<Post> allPost, SelectionType selectionType) {
        if (selectionType.equals(SelectionType.LATEST)) {
            sortByDate(allPost);
        } else if (selectionType.equals(SelectionType.RATING)) {

            List<Seller> sellers = sortSellerByRating();
            sellers.stream().forEach(s -> s.getPosts());
        } else if (selectionType.equals(SelectionType.PRICE)) {

        }
        return null;
    }

    List<Seller> sortSellerByRating() {
        return null;
    }

    private List<Post> sortByDate(List<Post> posts) {
        posts.sort((p1, p2) -> timeSort(p1, p2));
        return posts;
    }

    int timeSort(Post p1, Post p2) {
        if (p1.getPostDate().isEqual(p2.getPostDate())) {
            // higher id
            return p1.getId() - p2.getId();
        }
        boolean isBefore = p1.getPostDate().isBefore(p2.getPostDate());
        if (isBefore) {
            return 1;
        }
        return -1;
    }
}
