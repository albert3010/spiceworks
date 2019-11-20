package company.google;

import java.util.Comparator;

/**
 * Created by oyadav on 12/13/16.
 */
public class MyComparator implements Comparator<Object>{


    @Override
    public int compare(Object o1, Object o2){

        Integer e1 = (Integer) o1;
        Integer e2 = (Integer) o2;

        return e2.compareTo(e1);

    }


}
