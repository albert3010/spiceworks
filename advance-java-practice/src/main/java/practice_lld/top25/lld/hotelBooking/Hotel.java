package practice_lld.top25.lld.hotelBooking;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
public class Hotel {
    int hotelId;
    String hotelName;
    Address address;
    List<Room> rooms;
    double rating;
    Map<String, Boolean> isAvailable;
    List<Review> reviews;

}
