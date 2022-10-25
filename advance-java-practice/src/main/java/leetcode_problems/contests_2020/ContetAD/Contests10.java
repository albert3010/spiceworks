package leetcode_problems.contests_2020.ContetAD;

import org.junit.Test;

import java.util.*;

public class Contests10 {

    class Point implements Comparable<Point> {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point p) {
            if (this.x < p.x) {
                // +ve means swapping less val
                return 1;
            }
            if (this.x == p.x) {
                return p.y - this.y;
            }
            // no action on current element
            return -1;
        }
    }

    class Node implements Comparable<Node> {
        int x, y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Node p) {
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
        int s[] = {2, 10, 3, 1, 5, 8};
        int e[] = {5, 4, 3, 9, 7, 2};
//        System.out.println(maxPerformance(6, s, e, 2));
        int a[][] = {{4, 6}, {4, 3}, {1, 7}, {1, 4}};
//        int a[][] = {{2,2}, {1,6},{1,8},{1,3},{2,3},{1,10},{1,2},{1,5},{2,4},{2,10},{1,7},{2,5}};
//        System.out.println(maxNumberOfFamilies(4, a));

//        System.out.println(getKth(12, 15, 2));
        int grid[][] = {{1, 1, 2}};
//        System.out.println(hasValidPath(grid));
//        System.out.println(checkValidString("(())((())()()(*)(*()(())())())()()((()())((()))(*"));
        System.out.println(countInRange(2,10,15));
    }

    public int countInRange(int x, int y, int z) {
        int ans = 0;
        for (int i = y; i <= z; i++) {
            int n = i * x;
            if (!checkValid(n, i)) ans++;
        }
        return ans;
    }

    boolean checkValid(int a, int b) {
        Set<Integer> set = new LinkedHashSet<>();
        if (a == 0) set.add(a);
        while (a > 0) {
            set.add(a % 10);
            a = a / 10;
        }
        if (set.contains(b)) return true;
        while (b > 0) {
            if (set.contains(b % 10)) return true;
            b = b / 10;
        }
        return false;
    }

    public String shiftingLetters(String S, int[] shifts) {

        int n = shifts.length;
        int dp[] = new int[n];

        return "";


    }

    public boolean hasValidPath(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        if (n == 1 && m == 1) return true;
        int dir[] = {1, 2, 3, 5};
        Map<Integer, Boolean> map = new HashMap<>();
        map.put(1, true);
        map.put(2, true);
        map.put(3, true);
        map.put(5, true);
        Map<Integer, Integer> mapR = new HashMap<>();
        mapR.put(1, 3);
        mapR.put(3, 1);
        mapR.put(2, 5);
        mapR.put(5, 2);

        Map<Integer, Integer> mapD = new HashMap<>();
        mapD.put(1, 4);
        mapD.put(2, 7);
        mapD.put(3, 6);
        mapD.put(4, 8);
        mapD.put(5, 3);
        mapD.put(6, 5);
        int intPos = mapD.get(grid[0][0]);
        int s = 0;
        int i = 0;
        int j = 0;
        int d2 = 0;

        if (map.get(intPos - 1) != null) {

            d2 = intPos - 1;

        } else if (map.get(intPos - 2) != null) {

            d2 = intPos - 2;
        } else if (map.get(intPos - 3) != null) {

            d2 = intPos - 3;
        } else {
            return false;
        }
        if (d2 == 1) {
            j--;
        } else if (d2 == 2) {
            i--;
        } else if (d2 == 3) {
            j++;
        } else if (d2 == 5) {
            i++;
        }
        int f = 0;
        while (f == 0) {
            f = 0;

            if (i < 0 || j < 0 || i >= n || j >= m) break;
            intPos = mapD.get(grid[i][j]);
            if (map.get(intPos - 1) != null) {
                if (mapR.get(d2) == 1) {
                    d2 = intPos - 1;
                } else if (mapR.get(d2) == intPos - 1) {
                    d2 = 1;
                } else {
                    f = 1;
                }
            } else if (map.get(intPos - 2) != null) {
                if (mapR.get(d2) == 2) {
                    d2 = intPos - 2;
                } else if (mapR.get(d2) == intPos - 2) {
                    d2 = 2;

                } else {
                    f = 1;
                }
            } else if (map.get(intPos - 3) != null) {
                if (mapR.get(d2) == 3) {
                    d2 = intPos - 3;
                } else if (mapR.get(d2) == intPos - 3) {
                    d2 = 3;

                } else {
                    f = 1;
                }
            } else if (map.get(intPos - 5) != null) {
                if (mapR.get(d2) == 5) {
                    d2 = intPos - 5;
                } else if (mapR.get(d2) == intPos - 5) {
                    d2 = 5;
                } else {
                    f = 1;
                }
            }
            if (f == 1) return false;
            if (i == n - 1 && j == m - 1) return true;
            if (d2 == 1) {
                j--;
            } else if (d2 == 2) {
                i--;
            } else if (d2 == 3) {
                j++;
            } else if (d2 == 5) {
                i++;
            }
        }
        return false;
    }

    public boolean checkValidString(String s) {
        int openCountMin = 0;
        int openCountMax = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') {
                openCountMax++;
                openCountMin++;
            } else if (s.charAt(i) == ')') {
                openCountMax--;
                if (openCountMin > 0)
                    openCountMin--;
            } else {
                openCountMax++;
                if (openCountMin > 0)
                    openCountMin--;
            }
            if (openCountMax < 0) return false;
        }
        if (openCountMin <= 0 && openCountMax >= 0) return true;
        return false;
    }

    public int sumFourDivisors(int[] nums) {
        int n = nums.length;
        int ans = 0;
        for (int k = 0; k < n; k++) {
            int num = nums[0];
            int result = 0;
            int count = 0;
            for (int i = 1; i * i <= 10000; i++) {
                // if 'i' is divisor of 'num'
                if (count > 4) {
                    break;
                }
                if (num % i == 0) {
                    if (i == (num / i)) {
                        result += i;
                        count++;
                    } else {
                        result += (i + num / i);
                        count += 2;

                    }
                }
            }
            if (count == 4) {
                ans += result;
            }
        }
        return ans;
    }

    public int primes(int[] nums) {
        boolean noPrimes[] = new boolean[10000];
        noPrimes[1] = true;
        for (int i = 3; i < 10000; i += 2) {
            for (int j = i * i; j <= 10000; j += i) {
                noPrimes[j] = true;
            }
        }
        List<Integer> primes = new ArrayList<>();
        primes.add(2);

        return 1;
    }

    public int[] createTargetArray(int[] nums, int[] index) {
        int n = index.length;
        List<Integer> ans = new ArrayList<Integer>();

        for (int i = 0; i < n; i++) {
            ans.add(index[i], nums[i]);
        }
        for (int i = 0; i < n; i++) {
            index[i] = ans.get(i);
        }
        return index;
    }

    public int getKth(int lo, int hi, int k) {
        List<Integer> arr = new ArrayList<>();
        for (int i = lo; i <= hi; i++) {
            arr.add(i);
        }
        Collections.sort(arr, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return getPowSteps(o1.intValue()) - getPowSteps(o2.intValue());
            }
        });
        return arr.get(k - 1);
    }

    public int getPowSteps(int n) {
        int steps = 0;
        while (n > 1) {
            if (n % 2 == 0) {
                n = n / 2;
            } else {
                n = 3 * n + 1;
            }
            steps++;
        }
        return steps;
    }

    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        int ans = 0;
        int l = reservedSeats.length;
        Arrays.sort(reservedSeats, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        int count = 0;
        int f = 0;
        for (int i = 0; i < l; ) {
            int st[] = new int[10];
            int r = reservedSeats[i][0];
            while (i < l && r == reservedSeats[i][0]) {
                st[reservedSeats[i][1] - 1] = 1;
                i++;
                f = 1;
            }
            count += f;

            int x = 0;
            if (st[1] + st[2] + st[3] + st[4] == 0) {
                x++;

            }
            if (st[5] + st[6] + st[7] + st[8] == 0) {
                x++;
            }
            if (st[3] + st[4] + st[5] + st[6] == 0 && x == 0) {
                x++;
            }
            ans += x;
        }
        return ans + (n - count) * 2;
    }


    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {

        int n = arr1.length;
        int m = arr2.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            boolean f = true;
            for (int j = 0; j < m; j++) {
                if (Math.abs(arr1[i] - arr2[j]) <= d) {
                    f = false;
                }

            }
            if (f) ans++;
        }
        return ans;

    }

    public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
        int mod = 1000000000 + 7;
        List<Point> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new Point(efficiency[i], speed[i]));
        }
        Collections.sort(list);
        long ans = 0;
        int minSoFar = list.get(0).x;
        long sumSoFar = 0;
        PriorityQueue<Node> queue = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            queue.add(new Node(list.get(i).x, list.get(i).y));
            minSoFar = Math.min(list.get(i).x, minSoFar);
            sumSoFar = (sumSoFar + list.get(i).y);
            if (queue.size() > k) {
                Node nd = queue.poll();
                sumSoFar = sumSoFar - nd.y;
                minSoFar = Math.min(nd.x, minSoFar);
            }
            ans = Math.max(minSoFar * sumSoFar, ans);
        }
        return (int) ans % mod;
    }

}
