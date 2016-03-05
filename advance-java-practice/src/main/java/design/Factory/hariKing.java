package design.Factory;

/**
 * Created by omprakash.yadav on 19/02/16.
 */
public class HariKing implements  King{
    final String Description = "this is hari King";

    @Override
    public String getDescription(){
        return Description;
    }
}
