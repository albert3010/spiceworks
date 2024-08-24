package practice_lld.top25.lld.hotelBooking;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Address {
    int zipcode;
    Location location;
    String state;
    String street;

}
