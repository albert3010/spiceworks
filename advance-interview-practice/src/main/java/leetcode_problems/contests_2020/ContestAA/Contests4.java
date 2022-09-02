package leetcode_problems.contests_2020.ContestAA;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Contests4 {
    @Test
    public void ContestsSolution() {
//        String [] words =
        StringBuilder sb = new StringBuilder();
        sb.append("abc");
        sb.insert(3, "de");
//        sb.deleteCharAt(5);
        sb.delete(1, 3);
//        System.out.println(sb.toString());
        int [] tiles = {1,1,0,0,0,1,0};
        System.out.println(winner(tiles));

    }

    String winner(int[] tiles) {
        int n = tiles.length;
        List<Integer> countList = new ArrayList<>();
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (tiles[i] == 0) {
                count++;
            } else {
                if(count>0)
                countList.add(count);
                count = 0;
            }
        }
        if(count>0)
            countList.add(count);
        countList.sort((a, b) -> b-a);
        System.out.println(countList.get(0));
        int k= countList.size();
        System.out.println(k);
        if(k==0){
            return "No";
        }
        int max1 = countList.get(0);
        int max2 = 0;
        if(k>1){
             max2 = countList.get(1);

        }
        if( max1%2 == 0 || max1/2 < max2 ){
            return "No";
        }
        return "Yes";
    }

    String[] solution(String[][] queries) {
        int[] cr = new int[1];
        int n = queries.length;
        String[] ans = new String[n];
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            ans[i] = getResult(queries[i], sb, cr);

        }
        return ans;
    }

    String getResult(String[] queries, StringBuilder sb, int[] cr) {
        int p = cr[0];
        if (queries[0].equals("APPEND")) {
            sb.insert(p, queries[1]);
            cr[0] = cr[0] + queries[1].length();
        }
        if (queries[0].equals("MOVE")) {
            cr[0] = Math.min(sb.length(), Integer.parseInt(queries[1]));
        }
        if (queries[0].equals("FORWARD_DELETE")) {
            if (cr[0] < sb.length() - 1) {
                sb.deleteCharAt(cr[0] + 1);
            }
        }
        return sb.toString();
    }


//    public int subarraysWithKDistinct(int[] A, int K) {
//        int ct[] = new int[20001];
//        Queue map[] = new Queue[20001];
//        for (int i = 0; i < 20001; i++) {
//            map[i] = new LinkedList();
//        }
//        int start = 0;
//        int end = 0;
//        int n = A.length;
//        while (end < n) {
//            ct[A[end]]++;
//
//            if (checkMoreThanKDistinct(ct, start, end)) {
//                getCountOfSubArrays(A, map, start, end - 1, ct);
//            }
//            map[A[end]].add(end);
//            end++;
//        }
//
//    }

//    public int getCountOfSubArrays(int A[], Queue map[], int start, int end, int ct[]) {
//
//    }
//
//    public boolean checkMoreThanKDistinct(int ct[], int start, int end) {
//
//    }


    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int n = rooms.size();
        boolean[] v = new boolean[n];
        traverseRoomsDFSHelper(rooms, 0, v);
        for (int i = 0; i < n; i++) {
            if (v[i] == false) {
                return false;
            }
        }
        return true;
    }

    public void traverseRoomsDFSHelper(List<List<Integer>> rooms, int index, boolean[] v) {
        List<Integer> toGoRooms = rooms.get(index);
        v[index] = true;
        for (Integer room : toGoRooms) {
            if (!v[room]) {
                traverseRoomsDFSHelper(rooms, room, v);
            }
        }
    }

    public int numMatchingSubseq(String S, String[] words) {
        int n = words.length;
        int count = 0;

        for (int i = 0; i < n; i++) {

            String a = words[i];
            if (isPresent(S, a)) {
                count++;
            }
        }
        return count;
    }

    public boolean isPresent(String S, String a) {
        int x = S.length();
        int y = a.length();
        int i = 0;
        int j = 0;

        while (i < x && j < y) {
            if (S.charAt(i) == a.charAt(j)) {
                j++;
            }
            i++;
        }
        if (y == j) return true;
        return false;

    }
}
