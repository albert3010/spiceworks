package algorithms.new_problems.Polygon;

import java.util.ArrayList;
import java.util.List;

public class Rectangle extends Polygon {

    private double side1, side2;
    public Rectangle(double side1, double side2){
        this.side1 = side1;
        this.side2 = side2;
        List<Double> ss = super.getSides();
        ss.add(side1);
        ss.add(side2);
        ss.add(side1);
        ss.add(side2);
    }
    public double getArea(){
        return side1*side2;
    }
}
