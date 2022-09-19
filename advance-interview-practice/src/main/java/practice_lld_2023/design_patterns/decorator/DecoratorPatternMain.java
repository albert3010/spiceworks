package practice_lld_2023.design_patterns.decorator;

public class DecoratorPatternMain {

    public static void main(String[] args) {
        Car car = new BasicCar();
        car = new ManualCar(car);
        car = new SportsCar(car);
        car.assemble();
        System.out.println("\n*****");

        Car sportsLuxuryCar = new SportsCar(new LuxuryCar(new BasicCar()));
        sportsLuxuryCar.assemble();
    }
}
