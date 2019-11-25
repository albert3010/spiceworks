package algorithms.practice;

import java.util.*;

public class BookingHelper {

    public static HashMap<String, List<DriverCustomerRating>> getCustomersToDrivers(List<String> ridesDate) {

        HashMap<String, List<DriverCustomerRating>> customersToDrivers = new HashMap<>();

        for (String ride : ridesDate) {

            ride = ride.replaceAll(" ", "");

            String[] data = ride.split(",");
            String driverName = data[0];
            Double driverRating = Double.valueOf(data[1]);
            String customerName = data[2];
            Double customerRating = Double.valueOf(data[3]);

            DriverCustomerRating driverCustomerRating = new DriverCustomerRating(driverName, driverRating, customerRating);

            if (Objects.isNull(customersToDrivers.get(customerName))) {
                List<DriverCustomerRating> driversCustomerRatingList = new ArrayList<>();
                driversCustomerRatingList.add(driverCustomerRating);
                customersToDrivers.put(customerName, driversCustomerRatingList);
            } else {
                customersToDrivers.get(customerName).add(driverCustomerRating);
            }
        }
        return customersToDrivers;
    }

    public static HashMap<String, Double> getAllDriversRating(List<String> ridesDate) {

        HashMap<String, List<Double>> driversRatings = getPersonRatings(ridesDate, 0, 1);

        return getPersonsAvgRating(driversRatings);
    }

    public static HashMap<String, Double> getAllCustomersRating(List<String> ridesDate) {

        HashMap<String, List<Double>> customersRatings = getPersonRatings(ridesDate, 2, 3);

        return getPersonsAvgRating(customersRatings);
    }

    public static HashMap<String, List<Double>> getPersonRatings(List<String> ridesDate, int a, int b) {
        HashMap<String, List<Double>> personRatings = new HashMap<>();

        for (String ride : ridesDate) {
            ride = ride.replaceAll(" ", "");

            String[] data = ride.split(",");
            String personName = data[a];
            Double personRating = Double.valueOf(data[b]);

            if (Objects.isNull(personRatings.get(personName))) {

                List<Double> ratingList = new ArrayList<>();
                ratingList.add(personRating);
                personRatings.put(personName, ratingList);
            } else {
                personRatings.get(personName).add(personRating);
            }
        }
        return personRatings;
    }

    public static HashMap<String, Double> getPersonsAvgRating(HashMap<String, List<Double>> personRatings) {
        HashMap<String, Double> personAvgRating = new HashMap<>();

        for (String name : personRatings.keySet()) {
            Double avgRating = 0.0;
            int n = personRatings.get(name).size();
            for (Double rating : personRatings.get(name)) {
                avgRating += rating;
            }
            personAvgRating.put(name, avgRating / n);

        }
        return personAvgRating;
    }
}
