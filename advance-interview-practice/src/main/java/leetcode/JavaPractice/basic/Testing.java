package leetcode.JavaPractice.basic;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Testing {

    // A Generic method example
     <T, R, X> int genericDisplay (T element, R element2, X element3)
    {
        System.out.println(element.getClass().getName() +
            " = " + element);
        return 3;
    }

    @Test
    public void dietPlanPerformanceTest() {

        EnumSample Enm = EnumSample.HIGH;

        System.out.println(Enm.getLevelCode());
//        System.out.println(EnumSample.);

    }
   }