package practice_lld_2023.design_patterns.decorator;

public class ManualCar extends CarDecorator{

    public ManualCar(Car car) {
        super(car);
    }

    public void assemble(){
        super.assemble();
        System.out.println("Adding features of Manual Car.");
    }
}
