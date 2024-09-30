package test;
//BookingDetails (bookingId, JourneyId, userId, totalSeats, status, bookingDate)

import org.joda.time.LocalDateTime;

public class BookingDetails {
    private final int bookingId;
    private final BusJourney busJourney;
    private final int userId;
    private final int totalBookedSeats;
    private final String status;
    private final LocalDateTime bookingDateTime;

    public BookingDetails(int bookingId, BusJourney busJourney, int userId, int totalBookedSeats, String status, LocalDateTime bookingDateTime) {
        this.bookingId = bookingId;
        this.busJourney = busJourney;
        this.userId = userId;
        this.totalBookedSeats = totalBookedSeats;
        this.status = status;
        this.bookingDateTime = bookingDateTime;
    }
}
