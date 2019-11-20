package company.google;

import org.junit.Test;

/**
 * Created by oyadav on 12/13/16.
 */
//given 2 strings A and B. generate all possible solutions when B is merged in A. print all.

public class MergedABStrings {

    public static int count=0;
    @Test
    public void MergedABStringsTest() {

        String A = "abc";
        String B = "xyz";
        char[] result = new char[A.length() + B.length()];
        mergedABStringsUtils(A, B, 0, 0, result, 0);
    }

    public void mergedABStringsUtils(String A, String B, int Aindex, int Bindex, char[] result, int Rindex) {

        if(Rindex==A.length()+B.length()){

            for (int i=0;i<Rindex;i++){
                System.out.print(result[i]);
            }
            System.out.println();
            count++;
//            System.out.println(count);
            return;
        }

        if(Aindex <A.length()) {
            result[Rindex] = A.charAt(Aindex);
            mergedABStringsUtils(A, B, Aindex + 1, Bindex, result, Rindex + 1);
        }
        if(Bindex <B.length()) {
            result[Rindex] = B.charAt(Bindex);
            mergedABStringsUtils(A, B, Aindex, Bindex + 1, result, Rindex + 1);
        }
    }
}
