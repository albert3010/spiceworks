package practice.company.gojek.core;

import practice.company.gojek.Lot;
import practice.company.gojek.entities.Car;
import practice.company.gojek.entities.Customer;
import practice.company.gojek.entities.Ticket;

import java.util.HashMap;
import java.util.List;

/**
 * Created by OMPRAKASH on 10/1/2016.
 */
public class ParkingLot implements Lot {

    private static Integer parkingSize = 1000;
    private List<Integer> slots;
    private HashMap<Integer, Customer> occupiedSlots;
    private HashMap<Integer, Boolean> slotsAvailable;

    public ParkingLot() {
        init();
    }

    public ParkingLot(List<Integer> slots, HashMap<Integer, Customer> occupiedSlots, HashMap<Integer, Boolean> slotsAvailable) {
        this.slots = slots;
        this.occupiedSlots = occupiedSlots;
        this.slotsAvailable = slotsAvailable;
    }

    private void init() {
        for (Integer slot = 1; slot < parkingSize; slot++) {
            this.slots.add(slot);
            this.slotsAvailable.put(slot, true);
        }
    }

    public List<Integer> getSlot() {
        return slots;
    }

    public HashMap<Integer, Customer> getOccupiedSlots() {
        return occupiedSlots;
    }

    public HashMap<Integer, Boolean> getSlotsAvailable() {
        return slotsAvailable;
    }

    public Boolean isSlotAvailable(Integer slot){
       return slotsAvailable.get(slot);
    }

    public synchronized void addSlot(Integer slot){
        parkingSize++;
    }

    public Integer findNearestSlot(){
        for (Integer slot : slots){
            if(slotsAvailable.get(slot)){
               return slot;
            }
        }
        return 0;
    }
    public void parkCar(Car car){

        Integer nearestSlot = findNearestSlot();

        if(nearestSlot==0){
            System.out.println("No space left for parking_lot");
        }


        Ticket ticket = new Ticket("123",car, nearestSlot);

    }

}
