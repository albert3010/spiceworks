package service;

import entity.Address;
import entity.Product;

import java.util.HashMap;
import java.util.Map;

public class ProductService {

    Map<Integer, Product> productIdMap = new HashMap<>();

    public synchronized void increaseProductsToInventory(int productId, int count) throws Exception {
        Product product = productIdMap.get(productId);
        if (product != null) {
            int currentCount = product.getInventory();
            product.setInventory(currentCount + count);
            productIdMap.put(productId, product);
        } else {
            System.out.println("Product not found in inventory");
            throw new Exception("Product not found in inventory");
        }
    }

    public int createProduct(String name, double price, Address address, int inventory) {
        Product product = new Product(name, price, address, inventory);
        int id = product.getProductId();
        productIdMap.put(product.getProductId(), product);
        return id;
    }
    public void addProduct(Product product){
        productIdMap.put(product.getProductId(), product);
    }

    public Product getProduct(int productId) throws Exception {
        Product product = productIdMap.get(productId);
        if (product != null) {
            return productIdMap.get(productId);
        } else {
            System.out.println("Product not found in inventory");
            throw new Exception("Product not found in inventory");
        }
    }

    public boolean isInventoryAvailable(int productId, int quantity) throws Exception {

        Product product = productIdMap.get(productId);
        if (product != null) {
            int inventory = productIdMap.get(productId).getInventory();
            if (quantity <= inventory) return true;
            return false;
        } else {
            System.out.println("Product not found in inventory");
            throw new Exception("Product not found in inventory");
        }
    }

    public synchronized boolean reduceInventory(int productId, int quantity) throws Exception {
        Product product = productIdMap.get(productId);
        if (product != null) {
            int inventory = productIdMap.get(productId).getInventory();
            if (quantity > inventory) return false;

            int newInventory = inventory - quantity;
            product.setInventory(newInventory);
            productIdMap.put(productId, product);
            return true;
        } else {
            System.out.println("Product not found in inventory");
            throw new Exception("Product not found in inventory");
        }
    }


}
