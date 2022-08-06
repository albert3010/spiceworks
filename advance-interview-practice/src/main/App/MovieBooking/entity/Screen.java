package MovieBooking.entity;

import java.util.List;

public class Screen {
    int id;
    String name;
    Theater theater;
    List<Seat> seats;

    public Screen(int id, String name, Theater theater, List<Seat> seats) {
        this.id = id;
        this.name = name;
        this.theater = theater;
        this.seats = seats;
    }
}
