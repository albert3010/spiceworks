package practice.design.bridge;

/**
 * Created by omprakash.yadav on 11/03/16.
 */
public abstract class Shape {
    protected DrawApi drawApi;

    protected Shape(DrawApi drawApi){
        this.drawApi = drawApi;
    }
    public abstract  void draw();
}
