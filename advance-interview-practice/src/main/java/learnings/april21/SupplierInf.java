package learnings.april21;

import java.util.function.Supplier;

public class SupplierInf {
    public static void main(String[] args) {

        Supplier<User> userSupplier = () -> createUser();
        System.out.println(userSupplier.get().name);
        int a[] = {2, -1, 3, 0, 1, 2, 1};
        int b[] = {-1, 3, -1};

        System.out.println(countSubarray(b.length, 1, b));
    }

    public static User createUser() {
        return new User("om", 2);
    }

    static long countSubarray(int N, int K, int[] A) {
        // Write your code here
        int ans = 0;
        int dp[] = new int[N];
        dp[0] = A[0];
        for (int i = 1; i < N; i++) {
            dp[i] = dp[i - 1] + A[i];
        }
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                int sum = dp[j];
                if (i != 0) {
                    sum = dp[j] - dp[i - 1];
                }
                if (sum * K == j - i + 1) {
                    ans++;
                }
            }
        }
        return ans;
    }

}
