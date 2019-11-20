package tests;

import java.util.Comparator;
import java.util.StringJoiner;

/**
 * Created by OMPRAKASH on 9/24/2016.
 */

class MyComparator implements Comparator<Object> {

    @Override
    public int compare(Object o1, Object o2) {
            String s1=(String)o1;
            String s2=(String)o2;

        String xy  = s1+s2;
        String yx  = s2+s1;
            return yx.compareTo(xy);

    }

}