package MovieBooking.entity;

public class Seat {
    int id;
    SeatType seatType;

    public Seat(int id, SeatType seatType) {
        this.id = id;
        this.seatType = seatType;
    }
}
