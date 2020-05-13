package algorithms.new_problems.Polygon;

import java.util.ArrayList;
import java.util.List;

public class Square extends Rectangle {

    private double side1;
    public Square(double side1){
        super(side1, side1);
        this.side1 = side1;
    }
    public double getArea(){
        return side1*side1;
    }
}
