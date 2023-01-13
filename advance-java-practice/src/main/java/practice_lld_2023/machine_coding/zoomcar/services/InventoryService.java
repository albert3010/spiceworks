package practice_lld_2023.machine_coding.zoomcar.services;

import practice_lld_2023.machine_coding.zoomcar.entity.Inventory;
import practice_lld_2023.machine_coding.zoomcar.entity.Vehicle;
import practice_lld_2023.machine_coding.zoomcar.entity.VehicleAvailable;

import java.util.*;
import java.util.stream.Collectors;

public class InventoryService {

    Map<Integer, Inventory> inventoryMap = new HashMap<>();
    Map<Integer, Vehicle> vehicleMap = new HashMap<>();
    BookingService bookingService;

    public InventoryService(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public List<Inventory> getInventory() {
        return inventoryMap.entrySet().stream()
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

    public String addVehicleToInventory(Vehicle vehicle, int pickupLocationId, boolean isReady) {
        Inventory inventory = new Inventory(vehicle.getId(), pickupLocationId, isReady);
        inventoryMap.put(inventory.getId(), inventory);
        vehicleMap.put(vehicle.getId(), vehicle);
        return "inventory added : " + inventory.getId();
    }

    public Inventory getInventory(int inventoryId) {
        Inventory inventory = inventoryMap.get(inventoryId);
        Objects.nonNull(inventory);
        return inventory;
    }

    public void changePickupLocation(int inventoryId, int pickupId) {
        Inventory inventory = inventoryMap.get(inventoryId);
        Objects.nonNull(inventory);
        inventory.setPickupLocationId(pickupId);
        inventoryMap.put(inventoryId, inventory);
    }

    public List<VehicleAvailable> searchAvailableVehicles(Date startDate, Date endDate) {
        bookingService.getVehiclesBooking();
        return null;

    }

}
