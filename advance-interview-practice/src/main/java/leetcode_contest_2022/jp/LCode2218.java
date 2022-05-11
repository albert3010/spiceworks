package leetcode_contest_2022.jp;

import java.util.List;

public class LCode2218 {



    public static void main(String args[]) {




    }


    public int maxValueOfCoins(List<List<Integer>> piles, int k) {

        int index[] = new int[piles.size()];

        return recur(piles, k , index);

    }

    int recur(List<List<Integer>> piles, int k, int index[]) {
        if (k == 0)
            return 0;
        if (k == 1) {

            int max = 0;


            for (int i=0;i<piles.size();i++) {
                if (index[i] != piles.get(i).size() && piles.get(i).get(index[i]) > max)
                    max = piles.get(i).get(index[i]);
            }
            return max;

        }

        int max = 0;

        for (int i=0;i<piles.size();i++) {
            if (index[i] == piles.get(i).size()) {
                index[i]++;
                max = piles.get(i).get(index[i]-1) + Math.max(max, recur(piles, k - 1, index));
                index[i]--;
            }
        }

        return max;


    }

}
