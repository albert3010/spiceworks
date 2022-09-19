package practice_lld_2023.design_patterns.decorator;

public class SportsCar extends CarDecorator{

    public SportsCar(Car car) {
        super(car);
    }

    public void assemble(){
        super.assemble();
        System.out.println(" Adding features of Sports Car.");
    }
}
