package practice.design.bridge;

/**
 * Created by omprakash.yadav on 11/03/16.
 */
public class RedCircle implements DrawApi {

    @Override
    public void drawCircle(int radius, int x, int y){
        System.out.println("Drawing Circle[ color: red, radius: " + radius + ", x: " + x + ", " + y + "]");
    }

}
