package practice.tests;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * Created by OMPRAKASH on 9/24/2016.
 */class TestClass {
    public static void main(String args[] ) throws Exception {


        Scanner in = new Scanner(System.in);

        int n = in.nextInt();

        double a[] = new double[n];
        double b[] = new double[n];

        for(int i=0;i<n;i++){
            a[i]= in.nextDouble();
        }
        double min=0;
        double x;
        int c=0;
        for(int i=0;i<n;i++){

            b[i]= in.nextDouble();
            if(c==0){
                min = b[i]/a[i];

                c=1;
            }
            x= b[i]/a[i];

            if(min>x){
                min = x;
            }

        }
        double fl = in.nextDouble();

        double ans = fl/min;

        DecimalFormat df = new DecimalFormat();
        df.applyPattern(".000");
        System.out.println(df.format(ans));

        double y = Math.floor(ans * 1000) / 1000;
        System.out.println(y);



    }
}
