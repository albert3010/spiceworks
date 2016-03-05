package design.Factory;

/**
 * Created by omprakash.yadav on 19/02/16.
 */
public class HariArmy implements Army {

    final String DES = "this is hari Army";

    @Override
   public String getDescription(){
        return DES;
    }
}
