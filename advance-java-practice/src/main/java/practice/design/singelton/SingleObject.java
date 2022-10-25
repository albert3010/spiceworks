package practice.design.singelton;

/**
 * Created by omprakash.yadav on 10/03/16.
 */
public class SingleObject {

    private static SingleObject singleObject = new SingleObject();

    private SingleObject(){};

    public static SingleObject getInstance(){
        return singleObject;
    }
    public void getMassage(){
        System.out.println("this is singelton class..");
    }

}
