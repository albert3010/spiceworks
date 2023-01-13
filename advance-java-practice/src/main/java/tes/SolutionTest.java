package tes;

import java.util.*;

public class SolutionTest {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] strings = {"10", "0", "1"};
        int [] a ={1};

        List<Integer> intList = Arrays.asList(5, 8, 3, 2);
// To get max value
        int maxVal = Arrays.stream(a).max().getAsInt();


    }

    class Node {
        int i;
        int j;

        public Node(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    class Root {
        int val;
        List<Integer> indexes;

        public Root(int val) {
            this.val = val;
            this.indexes = new ArrayList<>();
        }

        void add(int index) {
            indexes.add(index);
        }
    }

    public int[][] findFarmland(int[][] land) {
        int m = land.length;
        int n = land[0].length;
        ArrayList<int[]> ans = new ArrayList<int[]>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (land[i][j] == 1) {

                    int col = j;
                    int row = i;
                    while (col < m && land[col++][row] == 1) ;
                    while (row < n && land[col][row++] == 1) ;
                    ans.add(new int[]{i, j, row - 1, col - 1});
                    System.out.println("--" + row + "__" + col);
                    for (int k = i; k < row; k++) {
                        for (int l = j; l < col; l++) {
                            land[k][l] = 3;
                        }
                    }
                }
            }

        }
        int len = ans.size();
        int[][] result = new int[len][4];
        int i = 0;
        for (int[] a : ans) {
            result[i++] = a;
        }
        return result;
    }

    List<Node> solve(int[] arr, int target) {
        Map<Integer, Root> map = new HashMap<>();
        List<Root> array = new ArrayList<>();
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            Root root = new Root(arr[i]);
            map.putIfAbsent(arr[i], root);
            map.get(arr[i]).add(i);
            array.add(root);
        }
        List<Node> ans = new ArrayList<>();
        Collections.sort(array, (a, b) -> a.val - b.val);

        int m = array.size();
        int st = 0;
        int end = m - 1;

        while (st < end) {
            Root a = array.get(st);
            Root b = array.get(end);
            int sum = a.val + b.val;
            int s1 = a.indexes.size();
            int s2 = b.indexes.size();
            if (s1 >= 2 && a.val * 2 == target) {

                for (int i = 0; i < s1; i++) {
                    for (int j = i + 1; j < s1; j++) {
                        ans.add(new Node(a.indexes.get(i), a.indexes.get(j)));
                    }
                }
            }
            if (s2 >= 2 && b.val * 2 == target) {

                for (int i = 0; i < s2; i++) {
                    for (int j = i + 1; j < s2; j++) {
                        ans.add(new Node(b.indexes.get(i), b.indexes.get(j)));
                    }
                }
            }
            if (sum == target) {
                for (int i = 0; i < s1; i++) {
                    for (int j = 0; j < s2; j++) {
                        ans.add(new Node(a.indexes.get(i), b.indexes.get(j)));
                    }
                }
                st++;
                end--;

            } else if (sum > target) {
                end--;
            } else {
                st++;
            }
        }
        if (st == end) {
            Root a = array.get(st);
            int s1 = a.indexes.size();
            if (s1 >= 2 && a.val * 2 == target) {

                for (int i = 0; i < s1; i++) {
                    for (int j = i + 1; j < s1; j++) {
                        ans.add(new Node(a.indexes.get(i), a.indexes.get(j)));
                    }
                }
            }
        }

        return ans;

    }

}
