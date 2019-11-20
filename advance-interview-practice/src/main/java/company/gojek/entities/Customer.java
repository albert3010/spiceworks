package company.gojek.entities;

import company.gojek.entities.Car;
import company.gojek.entities.User;

/**
 * Created by OMPRAKASH on 10/2/2016.
 */
public class Customer {

    private User user;
    private Car car;

    public Customer(User user, Car car) {
        this.user = user;
        this.car = car;
    }

    public User getUser() {
        return user;
    }

    public Car getCar() {
        return car;
    }
}
