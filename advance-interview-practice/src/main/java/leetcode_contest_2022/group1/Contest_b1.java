package leetcode_contest_2022.group1;

import org.junit.Test;

public class Contest_b1 {

    @Test
    public void ContestsSolution() {
        Tree tree = new Tree(8);
        Tree tree1 = new Tree(2);
        Tree tree2 = new Tree(6);
        Tree tree3 = new Tree(8);
        Tree tree4 = new Tree(7);
        Tree tree6 = new Tree(1);
        tree1.l =tree3;
        tree1.r =tree4;
        tree2.l = tree6;
        tree.l = tree1;
        tree.r = tree2;
        System.out.println(solution(tree));
    }

    class Tree {
        public int x;
        public Tree l;
        public Tree r;
        Tree(int x){
            this.x = x;
        }
    }

    public int solution(Tree T) {
        int ans[] = new int[1];
        traverse(T, T.x, ans);
        return ans[0];
    }

    private void traverse(Tree t, int mx, int[] ans) {
        if (t == null) return;
        int max = Math.max(t.x, mx);
        if (t.x >= mx) {
            ans[0]++;
        }
        traverse(t.l, max, ans);
        traverse(t.r, max, ans);
    }

    public int solution(int[] A) {
        int l = A.length;
        int ans = 1;
        for (int i = 0; i < l; i++) {
            if (A[i] == 0) return 0;
            if (A[i] < 0) {
                ans *= -1;
            }
        }
        return ans;
    }


}
