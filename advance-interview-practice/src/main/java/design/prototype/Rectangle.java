package design.prototype;

/**
 * Created by omprakash.yadav on 13/03/16.
 */
public class Rectangle extends Shape {


    public Rectangle(){
        type = "rectangle";
    }
    @Override
    public void draw(){
        System.out.println("this is rectangle");
    }
}
