package practice_lld_2023.design_patterns.decorator;

import java.util.*;

public class DecoratorPatternMain {

    public static void main(String[] args) {
//        Car car = new BasicCar();
//        car = new ManualCar(car);
//        car = new SportsCar(car);
//        car.assemble();
//        System.out.println("\n*****");
//
//        Car sportsLuxuryCar = new SportsCar(new LuxuryCar(new BasicCar()));
//        sportsLuxuryCar.assemble();

//        123.211.102.13
//        271.142.67.142
//        225.217.132.58
        System.out.println(getRegions(List.of("123.211.102.13", "271.142.67.142", "225.217.132.58")));

    }

    public static List<Integer> getRegions(List<String> ip_addresses) {
        int n = ip_addresses.size();
        List<Integer> ans = new ArrayList<>();

        for (String s : ip_addresses) {
            String[] parts = s.split("\\.");
            int a1 = Integer.valueOf(parts[0]);
            int a2 = Integer.valueOf(parts[1]);
            int a3 = Integer.valueOf(parts[2]);
            int a4 = Integer.valueOf(parts[3]);
            boolean f = isValid(a2, a3, a4);
            ;
            if (a1 >= 0 && a1 <= 127 && f) {
                ans.add(1);
            } else if (a1 >= 128 && a1 <= 191 && f) {
                ans.add(2);
            } else if (a1 >= 192 && a1 <= 223 && f) {
                ans.add(3);
            } else if (a1 >= 224 && a1 <= 239 && f) {
                ans.add(4);
            } else if (a1 >= 240 && a1 <= 255 && f) {
                ans.add(5);
            } else {
                ans.add(-1);
            }

        }
        return ans;
    }

    static boolean isValid(int a, int b, int c) {
        return (a >= 0 && a <= 255) && (b >= 0 && b <= 255) && (c >= 0 && c <= 255);
    }

    public static int getMinTime(List<Integer> task_memory, List<Integer> task_type, int max_memory) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        int n = task_memory.size();
        for (int i = 0; i < n; i++) {
            int mem = task_memory.get(i);
            int type = task_type.get(i);
            map.putIfAbsent(type, new ArrayList<>());
            map.get(type).add(mem);
        }
        int ans = 0;
        for (List<Integer> list: map.values()) {
            ans += findTime(list, max_memory);
        }

        return ans;
    }

    public static int findTime(List<Integer> list, int max) {
        Collections.sort(list);
        int n = list.size();
        int i = 0;
        int ans = 0;
        for (i = 0; i < n - 1; i = i + 2) {
            int x = list.get(i);
            int y = list.get(i + 1);
            if (x + y <= max) {
                ans++;
            } else {
                break;
            }
        }
        ans += (n - i);
        return ans;
    }

}
