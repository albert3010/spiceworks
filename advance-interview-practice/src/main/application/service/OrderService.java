package service;

import entity.Buyer;
import entity.Order;
import entity.PaymentMode;
import entity.Product;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@NoArgsConstructor
public class OrderService {

    BuyerService buyerService;
    ProductService productService;
    PincodeService pincodeService;
    Map<Integer, Order> orderMap;
    ReentrantReadWriteLock.ReadLock readLock;
    ReentrantReadWriteLock.WriteLock writeLock;


    public OrderService(BuyerService buyerService, ProductService productService,
                        PincodeService pincodeService) {
        this.orderMap = new HashMap<>();
        this.buyerService = buyerService;
        this.productService = productService;
        this.pincodeService = pincodeService;
        ReentrantReadWriteLock reentrantLock = new ReentrantReadWriteLock();
        this.readLock = reentrantLock.readLock();
        this.writeLock = reentrantLock.writeLock();
    }

    public int createOrder(int buyerId, int productId, int quantity, PaymentMode paymentMode) throws Exception {
        try {

            Buyer buyer = buyerService.getBuyer(buyerId);
            Product product = productService.getProduct(productId);
            boolean isOrderServiceable = checkServiceability(product, buyer, paymentMode);
            if (!isOrderServiceable) {
                throw new Exception("Order failed becoz pincode is unserviceable or payment method not accepted");
            }
            boolean isInventoryAvailable = productService.isInventoryAvailable(productId, quantity);
            writeLock.lock();
            if (!isInventoryAvailable) {
                throw new Exception("Order failed becoz inventory not available");
            }else{
                productService.reduceInventory(productId, quantity);
            }

            Order order = new Order(product, buyer, quantity);

            orderMap.put(order.getOrderId(), order);
            return order.getOrderId();
        } catch (Exception e) {
            throw new Exception("Order failed");
        } finally {
            writeLock.unlock();
        }
    }

    private boolean checkServiceability(Product product, Buyer buyer, PaymentMode paymentMode) {
        int sourcePincode = product.getAddress().getPinCode();
        int destinationPincode = buyer.getAddress().getPinCode();
        return pincodeService.isServiceability(sourcePincode, destinationPincode, paymentMode);
    }
    public Order getOrder(int orderId) throws Exception {
        Order order = orderMap.get(orderId);
        if(order!=null){
            return order;
        }else {
            throw new Exception("Order not found");
        }
    }


}
