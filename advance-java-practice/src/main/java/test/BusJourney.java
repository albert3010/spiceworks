package test;//BusJourney ( busId, date, startTime, endTime, source, destination, capacity, status)

import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
public class BusJourney {
    private final int busId;
    private final LocalDate journeyDate;
    private final LocalTime startTime;
    private final  LocalTime endTime;
    private final  String source;
    private String destination;
    int capacity;
    private String status;

    public BusJourney(int bustId, LocalDate journeyDate, LocalTime startTime, LocalTime endTime, String source, String destination, int capacity, String status) {
        this.busId = bustId;
        this.journeyDate = journeyDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.source = source;
        this.destination = destination;
        this.capacity = capacity;
        this.status = status;
    }
}
