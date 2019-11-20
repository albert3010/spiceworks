package Files.SaveImageURLToLocal;

/**
 * Created by omprakash.yadav on 07/03/16.
 */
public class MaximumTest {

    public static <T extends Comparable<T>>T maximum(T x, T y, T z){
        T max = x;
        if (max.compareTo(y) < 0) {
            max = y;
        }
        if (max.compareTo(z) < 0) {
            max = z;
        }
        return max;
    }
    public static void main( String args[] )
    {
        //hey this is me

        System.out.printf( "Max of %d, %d and %d is %d\n\n",
                3, 4, 5, maximum( 3, 4, 5 ) );

        System.out.printf( "Maxm of %.1f,%.1f and %.1f is %.1f\n\n",
                6.6, 8.8, 7.7, maximum( 6.6, 8.8, 7.7 ) );

        System.out.printf( "Max of %s, %s and %s is %s\n","pear",
                "apple", "orange", maximum( "pear", "apple", "orange" ) );
    }
}
