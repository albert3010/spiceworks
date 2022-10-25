package practice.company.google;

import org.junit.Test;

/**
 * Created by oyadav on 12/12/16.
 */

public class StringUtils {

    @Test
    public void stringUtilsTest(){

        String str1 = "Hello";
        String str2 = "He";

        boolean b = str1.startsWith(str2);

        if(b){
            System.out.println("Yes");
        } else System.out.println("No");
    }

}
