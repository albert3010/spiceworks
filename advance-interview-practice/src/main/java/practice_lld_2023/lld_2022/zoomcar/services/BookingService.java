package practice_lld_2023.lld_2022.zoomcar.services;

import practice_lld_2023.lld_2022.zoomcar.entity.Invoice;
import practice_lld_2023.lld_2022.zoomcar.entity.VehicleBooking;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class BookingService {

    Map<Integer, VehicleBooking> VehicleBookingMap = new HashMap<>();
    Map<Integer, Invoice> invoiceMap = new HashMap<>();

    public List<VehicleBooking> getVehiclesBooking() {
        return VehicleBookingMap.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
    }

    public String addVehicleBooking(VehicleBooking VehicleBooking) {
        VehicleBookingMap.put(VehicleBooking.getId(), VehicleBooking);
        return "VehicleBooking added : " + VehicleBooking.getId();
    }

    public VehicleBooking getVehicleBooking(int VehicleBookingId) {
        VehicleBooking VehicleBooking = VehicleBookingMap.get(VehicleBookingId);
        Objects.nonNull(VehicleBooking);
        return VehicleBooking;
    }

    public void cancelBooking(int vehicleBookingId) {
        VehicleBooking vehicleBooking = VehicleBookingMap.get(vehicleBookingId);
        Objects.nonNull(vehicleBooking);
        vehicleBooking.setCanceled(true);
        VehicleBookingMap.put(vehicleBookingId, vehicleBooking);
    }

    public boolean scanAndBook(String vehicleScanId){
        return true;
    }

    public boolean bookVehicle(int vehicleId){
        return true;
    }


}
