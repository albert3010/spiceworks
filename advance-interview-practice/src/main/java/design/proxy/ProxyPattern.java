package design.proxy;

/**
 * Created by omprakash.yadav on 13/03/16.
 */
public class ProxyPattern {
    public static void main(String[] args) {
        Image image = new ProxyImage("test_10mb.jpg");

        //image will be loaded from disk
        image.display();
        System.out.println("");

        //image will not be loaded from disk
        image.display();
    }
}
