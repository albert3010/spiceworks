package practice_lld_2023.design_patterns.decorator;

public class LuxuryCar extends CarDecorator{

    public LuxuryCar(Car car) {
        super(car);
    }

    public void assemble(){
        super.assemble();
        System.out.println(" Adding features of Luxury Car.");
    }
}
