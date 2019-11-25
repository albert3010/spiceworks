package algorithms.practice;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class CabBooking {

    @Test
    public void CabBookingTest() {
        List<String> ridesData = new ArrayList<>();
        ridesData.add("Raghu, 4, Amar, 5");
        ridesData.add("Raghu, 5, Akbar, 4");
        ridesData.add("Raghu, 1, Anthony, 2");
        ridesData.add("Ram, 5, Amar, 1");
        ridesData.add("Ram, 5, Akbar, 5");
        ridesData.add("Ram, 4, Anthony, 5");
        ridesData.add("Rajan, 3, Amar, 2");
        ridesData.add("Rajan, 4, Akbar, 5");
        ridesData.add("Rajan, 3, Anthony, 3");

        printAverageRatingAndEligibleDrivers(ridesData, "Akbar");

    }

    public void printAverageRatingAndEligibleDrivers(List<String> ridesData, String customerName) {


        HashMap<String, Double> driversRating = BookingHelper.getAllDriversRating(ridesData);
        System.out.println(driversRating);

        HashMap<String, Double> customersRating = BookingHelper.getAllCustomersRating(ridesData);
        System.out.println(customersRating);

        HashMap<String, List<DriverCustomerRating>> customersToDrivers = BookingHelper.getCustomersToDrivers(ridesData);
        System.out.println(customersToDrivers);

        Double customerRating = customersRating.get(customerName);

        List<String> drivers = getDriversForCustomer(driversRating, customerRating);

        filterOneRatingsDrivers(drivers, customersToDrivers.get(customerName));
        if (drivers.isEmpty()) {
            drivers = getDriversRatedByCustomer(customersToDrivers.get(customerName));
        }

        System.out.println("Average Rating of Customer: " + customerRating);
        if (drivers.isEmpty()) {
            System.out.println("No Cabs available");
        } else {
            System.out.print("Available Cab options based on rules defined: ");

            for (String name : drivers) {
                System.out.print(name + ",");
            }
        }

    }

    public List<String> getDriversRatedByCustomer(List<DriverCustomerRating> customerDriverRatings) {

        List<String> drivers = new ArrayList<>();

        for (DriverCustomerRating e : customerDriverRatings) {
            if (e.customerRating != 1.0 && e.driverRating != 1.0) {
                drivers.add(e.driverName);
            }
        }
        return drivers;
    }

    private void filterOneRatingsDrivers(List<String> drivers, List<DriverCustomerRating> customerDriverRatings) {

        HashMap<String, Boolean> driversMap = new HashMap<>();
        for (String s : drivers) {
            driversMap.put(s, true);
        }

        for (DriverCustomerRating e : customerDriverRatings) {
            if (!Objects.isNull(driversMap.get(e.driverName))) {
                if (e.customerRating == 1.0 || e.driverRating == 1.0) {
                    drivers.remove(e.driverName);
                }
            }
        }
    }

    private List<String> getDriversForCustomer(HashMap<String, Double> driversRating, Double customerRating) {

        List<String> drivers = new ArrayList<>();

        for (String name : driversRating.keySet()) {
            if (driversRating.get(name) != null) {
                if (customerRating <= driversRating.get(name)) {
                    drivers.add(name);
                }
            }
        }
        return drivers;
    }

}
