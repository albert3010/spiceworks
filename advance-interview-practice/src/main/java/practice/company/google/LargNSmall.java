package practice.company.google;

/**
 * Created by oyadav on 12/6/16.
 */

//Find the largest and smallest number in a list. The list is stored as two sections, one in ascending order and the other in descending order.
public class LargNSmall {

    public static void main(String[] args) {

        int a[] = new int[]{2, 3, 4, 5, 7, 6, 5, 3, 2, 1};

        int ans = findMax(a);
        System.out.println(ans);

    }

    private static int findMax(int a[]) {


        if (a.length == 1) return a[0];
        if (a.length == 2) return Math.max(a[0], a[1]);

        int l = a.length;
        int start = 0;
        int end = l;

        int mid = (start + end) / 2;

        while (!(a[mid] > a[mid - 1] && a[mid] > a[mid + 1])) {

            if (a[mid] < a[mid + 1]) {
                start = mid;
                mid = (start + end) / 2;
            } else {
                end = mid;
                mid = (start + mid) / 2;

            }
        }
        return a[mid];
    }
}
