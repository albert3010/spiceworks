import java.util.List;
import java.util.Stack;

public class Solution {

    public static void main(String[] args) {
        System.out.println("test");
        int[] arr = {0,0,0,1};
//        System.out.println(getNextGreater(arr));
        System.out.println(findNoOfOnes(arr));
    }

    static int[] getNextGreater(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        int n = arr.length;
        int[] ans = new int[n];
        int k = n - 1;
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[i] >= stack.peek()) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                ans[k--] = -1;
            } else {
                ans[k--] = stack.peek();
            }
            stack.add(arr[i]);
        }
        return ans;
    }

    static int findNoOfOnes(int[] arr) {
        int n = arr.length;
        int start = 0;
        int end = n - 1;

        while (start < end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] == 0) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        if (arr[start] == 0) {
            return n - (start + 1);
        }
        return n - start;
    }
}
