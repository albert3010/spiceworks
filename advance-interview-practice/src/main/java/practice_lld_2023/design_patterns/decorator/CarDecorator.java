package practice_lld_2023.design_patterns.decorator;


public class CarDecorator implements Car{

    protected Car car;

    public CarDecorator(Car car){
        this.car = car;
    }

    public void assemble() {
        this.car.assemble();
    }
}
