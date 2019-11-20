package design.prototype;

/**
 * Created by omprakash.yadav on 13/03/16.
 */
public class Square extends Shape {

    public Square(){
        type = "square";
    }

    @Override
    public void draw(){
        System.out.println("this is draw inside square");
    }
}
