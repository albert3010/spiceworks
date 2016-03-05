package design.Factory;

/**
 * Created by omprakash.yadav on 19/02/16.
 */
public class OmKing implements  King{
    final String Description = "this is om King";

    @Override
    public String getDescription(){
        return Description;
    }
}
