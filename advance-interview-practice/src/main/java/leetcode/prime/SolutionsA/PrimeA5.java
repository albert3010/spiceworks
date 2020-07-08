package leetcode.prime.SolutionsA;

import org.junit.Test;

import java.util.*;

public class PrimeA5 {

    public static void main(String[] args) {

    }
       public class TreeNode {
       int val;
       TreeNode left;
       TreeNode right;
       TreeNode() {}
       TreeNode(int val) { this.val = val; }
       TreeNode(int val, TreeNode left, TreeNode right) {
           this.val = val;
           this.left = left;
           this.right = right;
       }
   }
    public List<List<Integer>> levelOrderBottom(TreeNode root) {

        Queue<TreeNode> queue = new LinkedList();
        Stack<List<Integer>> stack = new Stack();

        List<List<Integer>> ans = new ArrayList<>();

        if(root!=null){
            queue.add(root);
        }

        while(!queue.isEmpty()){
            int l = queue.size();
            List<Integer> list = new ArrayList<>();
            while(l-->0){


                TreeNode nd = queue.poll();
                list.add(nd.val);
                if(nd.left!=null){
                    queue.add(nd.left);
                }

                if(nd.right!=null){
                    queue.add(nd.right);
                }
            }
            stack.add(list);
        }

        while(!stack.isEmpty()){
            ans.add(stack.pop());
        }
        return ans;
    }
}
