package practice.design.prototype;

/**
 * Created by omprakash.yadav on 13/03/16.
 */
public class Circle extends Shape {

    public Circle(){
        type = "circle";
    }
    @Override
    public void draw(){
        System.out.println("this is draw inside circle");
    }
}
