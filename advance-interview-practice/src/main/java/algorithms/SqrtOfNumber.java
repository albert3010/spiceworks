package algorithms;

/**
 * Created by OMPRAKASH on 9/25/2016.
 */
public class SqrtOfNumber {

    public static void main(String[] args) {

        int n = 3;

        System.out.println(mySqrt(n));

    }
    private static double mySqrt(int n){

        double m = n;
        double l = 0;
        double h = n;
        double mid=0;


        while (l<h && (h-l)>0.01){

            mid = (l+h)/2;

            if(mid*mid==m){
                return mid;
            }

            if(mid*mid>m){
                 h=mid;
            } else{
                l=mid;
            }

        }
        return mid;
    }
}
