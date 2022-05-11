package lld_2022.zoomcar.entity;

import lombok.Data;

@Data
public class VehiclePrice {

    int vehicleId;
    Double pricePerHour;
    String currency;

    public VehiclePrice(int vehicleId, Double pricePerHour, String currency) {
        this.vehicleId = vehicleId;
        this.pricePerHour = pricePerHour;
        this.currency = currency;
    }
}