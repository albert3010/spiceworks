package collections.set;

import lombok.Getter;
import lombok.Setter;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by omprakash.yadav on 05/03/16.
 */


public class SetSample {

    public static void main(String[] args) {

    List<String> l1 = new ArrayList<String>();

    Set<String> ss = new HashSet<>();
    ss.add("mango");
    ss.add("apple");
        System.out.println(ss);
    }
    @Test
   public void arrayListCollection(){

        List<String> l1 = new ArrayList<>();
        l1.add("flipkart");
        l1.add("snapdeal");
        l1.add("amazon");
        System.out.println("List of online retailers : "+ l1 );

    }
    @Test
    public void setCollection(){

        if(People.KID.equals("KID")){
            System.out.println("Its KID");
        }


        Set<Number> s1 = new HashSet<>();
        String st = "sample";
        Number n = 1;
        s1.add(1);
        s1.add(2);
        if(s1.isEmpty() == false){
            System.out.println(s1);
        }
        if(s1.contains(n) == true){
            System.out.println("It contains " + n);
        }

        String s[] = {"a","b","c"};

        Set<List> s2 = new HashSet(Arrays.asList(s));
        String ss[] = {"a","b","6"};

        Set<List> s3 = new HashSet(Arrays.asList(ss));
        Set<List> s4 = new HashSet();

        if(s2.containsAll(s3) == true){
            System.out.println("contains");
        }
        else
            System.out.println("not contains");

        s4.addAll(s3);
        System.out.println(s4);
        s4.hashCode();
        System.out.println(s4);

        Dog dog = new Dog("white");
        Object dog1 = new Dog("black");
    if( dog1 instanceof Dog){
        System.out.println("true");
    }

      int hash=  s1.hashCode();
        int hash1 = s2.hashCode();
        int hash2 = s3.hashCode();
        System.out.println("Hash code is : "+ hash);
        System.out.println("Hash code is : "+ hash1);
        System.out.println("Hash code is : "+ hash2);



    }

    class Dog{
        String color;

        public Dog(String s){
            color = s;
        }

        //overridden method, has to be exactly the same like the following
        public boolean equals(Object obj) {
            if (!(obj instanceof Dog))
                return false;
            if (obj == this)
                return true;
            return this.color.equals(((Dog) obj).color);
        }

    }

}
