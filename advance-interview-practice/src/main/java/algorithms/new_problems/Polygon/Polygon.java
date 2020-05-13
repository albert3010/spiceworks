package algorithms.new_problems.Polygon;

import java.util.ArrayList;
import java.util.List;

abstract public class Polygon {

    private List<Double> sides;

    public List<Double> getSides() {
        return this.sides;
    }

    public Polygon(List<Double> sides) {
        this.sides = sides;
    }
    public Polygon() {
        this.sides = new ArrayList<>();
    }

    abstract public double getArea();

}
