package practice.design.singelton;

/**
 * Created by omprakash.yadav on 10/03/16.
 */
public class SingletonPattern {

    public static void main(String[] args) {
    SingleObject singleObject =  SingleObject.getInstance();
        singleObject.getMassage();
    }
}
