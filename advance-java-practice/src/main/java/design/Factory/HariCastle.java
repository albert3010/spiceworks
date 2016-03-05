package design.Factory;

/**
 * Created by omprakash.yadav on 19/02/16.
 */
public class HariCastle implements Castle {

    final String Description = "this is heri castle";

    @Override
    public String getDescription(){
        return Description;
    }
}
