package practice_lld.top25.lld.hotelBooking;

import practice_lld.top25.lld.hotelBooking.enums.PaymentStatus;
import java.util.List;

public class Booking {
    int bookingId;
    int userId;
    int hotelId;

    List<Room> bookedRooms;
    double amount;
    PaymentStatus paymentStatus;
}
