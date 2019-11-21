package practice.design.bridge;

/**
 * Created by omprakash.yadav on 11/03/16.
 */
public class Circle extends Shape {
    private int x, y, radius;
    public  Circle(int x, int y, int radius, DrawApi drawApi){
        super(drawApi);
        this.x=x;
        this.y=y;
        this.radius=radius;
    }

    public  void draw(){
       drawApi.drawCircle(radius,x,y);
    }
}
