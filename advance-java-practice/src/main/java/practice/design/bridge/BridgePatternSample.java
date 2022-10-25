package practice.design.bridge;

/**
 * Created by omprakash.yadav on 11/03/16.
 */
public class BridgePatternSample {

    public static void main(String[] args) {
        Shape redCircle = new Circle(1,2,3,new RedCircle());
        Shape greenCircle = new Circle(1,2,3,new GreenCircle());
        redCircle.draw();
        greenCircle.draw();
    }
}
