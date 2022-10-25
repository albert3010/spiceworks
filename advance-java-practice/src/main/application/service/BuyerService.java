package service;

import entity.Address;
import entity.Buyer;
import entity.Product;

import java.util.HashMap;
import java.util.Map;

public class BuyerService {
    Map<Integer, Buyer> buyerIdMap = new HashMap<>();

    public int addBuyer(String name , Address address){
        Buyer buyer = new Buyer(name, address);
        int buyerId = buyer.getBuyerId();
        buyerIdMap.put(buyerId, buyer);
        return buyerId;
    }
    public void addBuyer(Buyer buyer){
        buyerIdMap.put(buyer.getBuyerId(), buyer);
    }
    public Buyer getBuyer(int buyerId) throws Exception {
        Buyer buyer = buyerIdMap.get(buyerId);
        if(buyer!=null){
            return buyer;
        }
        throw new Exception("Buyer not found");
    }

}
