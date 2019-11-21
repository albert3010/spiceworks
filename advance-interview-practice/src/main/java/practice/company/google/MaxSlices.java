package practice.company.google;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by oyadav on 12/13/16.
 */
//Given a list of pies (and the number of slices in each pie) calculate the maximum number of slices that nPeople could receive if each person got the same amount of slices and did not get slices from more than 1 pie.

public class MaxSlices {

    @Test
    public void maxSlicesTest() {

        List<Integer> pieSlices = new ArrayList<>();

        pieSlices.add(4);
        pieSlices.add(5);
        pieSlices.add(3);
        pieSlices.add(16);
        pieSlices.add(2);
        pieSlices.add(20);

        int npeople = 8;
        int maxSlices = getMaxSlices(pieSlices, npeople);

        System.out.println("Max slices for n people -> "+maxSlices);

    }
    private int getMaxSlices(List<Integer> pieSlices, int npeople) {

        Collections.sort(pieSlices, new MyComparator());
//        System.out.println(pieSlices.get(0)); will give max value
        int maxSlice = pieSlices.get(0) / npeople;

        int peopleLeft = npeople;

        while (true){

            if(maxSlice==0) maxSlice++;

            int tmpPeople = pieSlices.get(0)/maxSlice;

            if(tmpPeople==0) break;
            peopleLeft = npeople;
            for(Integer v: pieSlices){

               tmpPeople = v/maxSlice;

                peopleLeft = peopleLeft-tmpPeople;

                if (peopleLeft<=0 || tmpPeople==0)
                    break;
            }
            if(peopleLeft>0 && tmpPeople==0) break;
            maxSlice++;
        }

        return maxSlice-1;

    }

}
