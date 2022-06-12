package entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Address {
    int id;
    String address;
    int pinCode;
    public Address(int pinCode, String address){
        this.pinCode = pinCode;
        this.address = address;
        this.id = IdCounters.getAddressId();
    }
}
