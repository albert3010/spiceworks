package algorithms.new_problems.Polygon;

public class Test {

    @org.junit.Test
    public void test(){

        Polygon polygonr = new Rectangle(3,5);

        System.out.println(polygonr.getArea());
        System.out.println(polygonr.getSides());

        Polygon polygons = new Square(3);

        System.out.println(polygons.getArea());
        System.out.println(polygons.getSides());
    }
}
