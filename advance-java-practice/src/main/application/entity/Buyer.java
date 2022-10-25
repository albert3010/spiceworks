package entity;

import lombok.Data;

@Data
public class Buyer {
    int buyerId;
    String name;
    Address address;

    public Buyer(String name, Address address){
        this.name = name;
        this.address = address;
        this.buyerId = IdCounters.getBuyerId();
    }
}
