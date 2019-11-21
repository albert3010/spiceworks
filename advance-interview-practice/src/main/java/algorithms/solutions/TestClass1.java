package algorithms.solutions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Created by OMPRAKASH on 9/8/2016.
 */
public class TestClass1 {

    Integer swapLeft;

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);  // Reading from System.in

        int N = reader.nextInt(); // Scans the

        Integer k = reader.nextInt();

        ArrayList<Integer> a = convertToAray(N);

        int ans = HelperUtil(a, k);

        System.out.println(ans);
    }

    private static int findValueGreaterInRight(ArrayList<Integer> a, int curr, int startIndex, Integer swapLeft) {

        int size = a.size();

        if (startIndex >= size) return -1;

        int maxGreaterDigit = -1;

        int k = swapLeft;

        for (int i = startIndex; i < size && k > 0; i++) {
                k--;
            int val = a.get(i);

            if (val == 9) {

                maxGreaterDigit = 9;
                break;
            } else if (curr < val && maxGreaterDigit < val) {

                maxGreaterDigit = val;

            }


        }
        if (maxGreaterDigit == -1)
            return -1;

        for (int i = startIndex; i < size && swapLeft > 0; i++) {
            swapLeft--;

            if (maxGreaterDigit == a.get(i)) {
                return maxGreaterDigit;
            }
        }
        return -1;

    }

    private static int  findIndexOfMaxVal(ArrayList<Integer>a ,int  maxVal, int start){

        int size =  a.size();

        for (int i = start; i < size ; i++) {


            if (maxVal == a.get(i)) {
                return i;
            }
        }
        return -1;
    }
  private static void  siftDigitsByPuttingMaxValAtStart(ArrayList<Integer>a, int start, int lastIndex){

      for(int i= lastIndex; i>start;i--){
          //shift
          Collections.swap(a, i,i-1);

      }
    }
    private static int HelperUtil(ArrayList<Integer> a, Integer k) {


        int size = a.size();



        for (int i = 0; i < size; i++) {

            int curr = a.get(i);


            int maxVal = findValueGreaterInRight(a, curr, i + 1, k);

            if (maxVal == -1)
            {
                int number=0;
                int tmp=1;

                for(int x=0 ; x < size; x++){

                    number = number*tmp+a.get(i);
                    tmp= tmp*10;
                }

                return number;

            }

            for (int ii = i+1; ii < size && k > 0; ii++) {
                  k--;

                if (maxVal == a.get(ii)) {
                    break;
                }
            }
            int lastIndex = findIndexOfMaxVal(a, maxVal, i + 1);

            siftDigitsByPuttingMaxValAtStart(a, i, lastIndex);
  }
  return -1;
    }

    public static ArrayList<Integer> convertToAray(int N) {

        int temp = N;
        ArrayList<Integer> array = new ArrayList<Integer>();
        do {
            array.add(temp % 10);
            temp /= 10;
        } while (temp > 0);
        Collections.reverse(array);
        return array;
    }

}
