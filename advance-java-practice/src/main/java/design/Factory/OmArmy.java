package design.Factory;

/**
 * Created by omprakash.yadav on 19/02/16.
 */
public class OmArmy implements Army {

    final String Description = "this is om Army";

    @Override
   public String getDescription(){
        return Description;
    }
}
