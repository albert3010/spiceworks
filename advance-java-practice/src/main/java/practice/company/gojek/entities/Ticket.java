package practice.company.gojek.entities;

/**
 * Created by OMPRAKASH on 10/2/2016.
 */
public class Ticket {

    private String ticketId;
    private Car car;
    private Integer slot;

    public Ticket(String ticketId, Car car, Integer slot) {
        this.ticketId = ticketId;
        this.car = car;
        this.slot = slot;
    }

    public String getTicketId() {
        return ticketId;
    }

    public Car getCar() {
        return car;
    }

    public Integer getSlot() {
        return slot;
    }
}
