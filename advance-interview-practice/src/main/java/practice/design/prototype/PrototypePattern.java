package practice.design.prototype;

/**
 * Created by omprakash.yadav on 13/03/16.
 */
public class PrototypePattern {

    public static void main(String[] args) {
        Shapecache.loadCache();

        Shape clonedShape = (Shape) Shapecache.getShape("1");
        System.out.println("Shape : " + clonedShape.getType());

        Shape clonedShape2 = (Shape) Shapecache.getShape("2");
        System.out.println("Shape : " + clonedShape2.getType());

        Shape clonedShape3 = (Shape) Shapecache.getShape("3");
        System.out.println("Shape : " + clonedShape3.getType());


    }


}
