package leetcode_problems.june_contests.S1;

import org.junit.Test;

public class Solution7 {
    @Test
    public void test(){

        System.out.println(deserialize("(2)(#0#$)($)"));
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    public TreeNode deserialize(String data) {
        System.out.println(data);
        int l = data.length();
        if(l==0 || data.charAt(0)=='$'){
            return null;
        }


        int e = data.indexOf(')');

        String num = data.substring(1, e);

        int val =  Integer.parseInt(num);

        TreeNode node  = new TreeNode(val);

        int s1 = data.indexOf('#');

        int e1 = data.indexOf('#', s1+1);

        String num1 = data.substring(s1+1, e1);

        int val1 =  Integer.parseInt(num1);

        if(val1!=0){
            String sleft = data.substring(e1+1, e1+1+val1);
            node.left = deserialize(sleft);
        } else{
            val1=1;
        }

        String sright = data.substring(e1+1+val1+2, l-1);
        node.right = deserialize(sright);
        return node;

    }
//    public class Codec {
//
//        // Encodes a tree to a single string.
//        public String serialize(TreeNode root) {
//            serializeHelper(root, "");
//
//
//        }
//
//        String serializeHelper(TreeNode root, String s) {
//            if (root == null)
//                return "($)";
//
//            String sl = serializeHelper(root.left, s);
//            String sr = serializeHelper(root.right, s);
//            String ss = Integer.toString(root.val);
//
//            String t = Integer.toString(sl.length());
//
//            if (sl.charAt(1) == '$') {
//                t = "";
//            }
//            return "(" + ss + ")" + "(" + t + sl + ")" + "(" + sr + ")";
//        }
//
//        // Decodes your encoded data to tree.
//        public TreeNode deserialize(String data) {
//
//
//        }
//    }

}

