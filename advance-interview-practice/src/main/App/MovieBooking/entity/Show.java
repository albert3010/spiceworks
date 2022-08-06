package MovieBooking.entity;

import java.util.Date;

public class Show {
    int id;
    Movie movie;
    Screen screen;
    Date startTime;
    Date endTime;

    public Show(int id, Movie movie, Screen screen, Date startTime, Date endTime) {
        this.id = id;
        this.movie = movie;
        this.screen = screen;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
