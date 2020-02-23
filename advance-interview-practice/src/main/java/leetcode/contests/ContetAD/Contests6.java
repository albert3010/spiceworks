package leetcode.contests.ContetAD;

import org.junit.Test;

import java.awt.image.ImageProducer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Contests6 {
    class Point implements Comparable<Point> {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point p) {
            if (this.y > p.y) {
                // +ve means swapping less val
                return 1;
            }
            if (this.y == p.y) {
                return this.x - p.x;
            }
            // no action on current element
            return -1;
        }
    }

    @Test
    public void ContestsSolution() {
        System.out.println(numberOfSubstrings("abc"));
    }
    public int countOrders(int n) {
        int mod = 1000000000 + 7;

       return (int)factorial(2*n, mod);

    }
    public long factorial(int n, int mod)
    {
        if (n == 0)
            return 1;
        int m = n;
        if(n%2==0){
             m = n/2;
        }
        return (m*factorial(n-1, mod))%mod;
    }
    long power(int x,  int y, int mod)
    {
        long temp;
        if( y == 0)
            return 1;
        temp = power(x, y/2, mod);
        if (y%2 == 0)
            return (temp*temp)%mod;
        else
            return (x*temp*temp)%mod;
    }


    public int numberOfSubstrings(String s) {
        int[] count = new int[3];
        int n = s.length();
        int prev = 0;
        int current = 2;
        int ans = 0;
        count[s.charAt(0) - 'a']++;
        count[s.charAt(1) - 'a']++;
        count[s.charAt(2) - 'a']++;
        while (current<n) {
            if (checkAtLeastOne(count)) {
                int countX = n - current;
                ans += countX;
                count[s.charAt(prev) - 'a']--;
                prev++;
            } else {
                current++;
                if(current==n) break;
                count[s.charAt(current) - 'a']++;
            }
        }
        return ans;
    }

    public boolean checkAtLeastOne(int[] count) {
        for (int i = 0; i < 3; i++) {
            if (count[i] < 1) {
                return false;
            }
        }
        return true;
    }

    class Cashier {
        HashMap<Integer, Integer> productsToPrice;
        int discount;
        int counter;
        int total;

        public Cashier(int n, int discount, int[] products, int[] prices) {
            this.productsToPrice = new HashMap<>();
            this.discount = discount;
            this.counter = 0;
            this.total = n;
            int m = products.length;
            for (int i = 0; i < m; i++) {
                productsToPrice.put(products[i], prices[i]);
            }

        }

        public double getBill(int[] product, int[] amount) {
            counter++;
            int m = product.length;
            int discoutApply = 0;
            if (counter >= total && counter % total == 0) {
                discoutApply = discount;
            }
            double totalBill = 0;
            for (int i = 0; i < m; i++) {
                totalBill += productsToPrice.get(product[i]) * amount[i];
            }
            totalBill = totalBill - ((discoutApply * totalBill) / 100);
            return totalBill;
        }
    }


    public int[] sortByBits(int[] arr) {
        int n = arr.length;
        List<Point> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            list.add(new Point(arr[i], getBits(arr[i])));
        }
        Collections.sort(list);
        for (int i = 0; i < n; i++) {
            arr[i] = list.get(i).x;
        }
        return arr;
    }

    public int getBits(int n) {
        int count1 = 0;
        while (n > 0) {
            if (n % 2 == 1) {
                count1++;
            }
            n = n / 2;
        }
        return count1;
    }
}
