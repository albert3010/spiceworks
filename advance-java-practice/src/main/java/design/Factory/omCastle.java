package design.Factory;

/**
 * Created by omprakash.yadav on 19/02/16.
 */
public class OmCastle implements Castle {

    final String Description = "this is om castle";

    @Override
    public String getDescription(){
        return Description;
    }
}
