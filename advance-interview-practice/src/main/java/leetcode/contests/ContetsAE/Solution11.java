package leetcode.contests.ContetsAE;

import com.sun.corba.se.spi.orbutil.threadpool.NoSuchThreadPoolException;

import java.io.*;
import java.util.ArrayList;
import java.util.*;
import java.util.List;
import java.util.StringTokenizer;
import java.util.function.Function;
import java.util.stream.IntStream;
// 24704490
class Sollution11 {
    private static class Reader implements Closeable {
        private BufferedReader br;
        private StringTokenizer st;

        public Reader(InputStream is) {
            br = new BufferedReader(new InputStreamReader(is));
        }

        public void readLine() {
            try {
                st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public String next() {
            if (st == null || !st.hasMoreTokens()) {
                readLine();
            }
            return st.nextToken();
        }

        public <T> T next(Function<String, T> parser) {
            return parser.apply(next());
        }

        public int nextInt() {
            return next(Integer::valueOf);
        }

        public long nextLong() {
            return next(Long::valueOf);
        }

        public double nexDouble() {
            return next(Double::valueOf);
        }

        @Override
        public void close() throws IOException {
            br.close();
        }
    }

    private static final int MOD = 1000000000 + 7;

    private static boolean[] IS_PRIME = new boolean[45];

    static {
        for (int x = 2; x < 45; x++) {
            IS_PRIME[x] = true;
            for (int d = 2; d * d <= x; d++) {
                if (x % d == 0) {
                    IS_PRIME[x] = false;
                    break;
                }
            }
        }
    }

    private static int sum(int[] arr, int from, int to) {
        return IntStream.rangeClosed(from, to).map(i -> arr[i]).sum();
    }

    private static int[] digits5 = new int[5];

    private static boolean isGood5(int x) {
        for (int i = 4; i >= 0; i--) {
            digits5[i] = x % 10;
            x /= 10;
        }
        return IS_PRIME[sum(digits5, 0, 2)] && IS_PRIME[sum(digits5, 1, 3)] && IS_PRIME[sum(digits5, 2, 4)]
                && IS_PRIME[sum(digits5, 0, 3)] && IS_PRIME[sum(digits5, 1, 4)]
                && IS_PRIME[sum(digits5, 0, 4)];
    }

    private static int[] digits4 = new int[4];

    private static boolean isGood4(int x) {
        for (int i = 3; i >= 0; i--) {
            digits5[i] = x % 10;
            x /= 10;
        }
        return IS_PRIME[sum(digits5, 0, 2)] && IS_PRIME[sum(digits5, 1, 3)]
                && IS_PRIME[sum(digits5, 0, 3)];
    }

    private static int[] digits3 = new int[3];

    private static boolean isGood3(int x) {
        for (int i = 2; i >= 0; i--) {
            digits5[i] = x % 10;
            x /= 10;
        }
        return IS_PRIME[sum(digits5, 0, 2)];
    }


    private static int[] GOOD5 = new int[1000];
    private static int[][] GOOD5_CONNECTED;

    static {
        int[] GOOD5_IDX = new int[100000];
        Arrays.fill(GOOD5_IDX, -1);
        for (int x = 0; x < 100000; x++) {
            if (isGood5(x)) {
                GOOD5_IDX[x] = ++GOOD5[0];
                GOOD5[GOOD5[0]] = x;
            }
        }
        int size = GOOD5[0];
        GOOD5_CONNECTED = new int[size + 1][];
        for (int i = 1; i <= size; i++) {
            GOOD5_CONNECTED[i] = new int[size + 1];
        }
        for (int i = 1; i <= size; i++) {
            for (int end = 0; end < 10; end++) {
                int next = 10 * (GOOD5[i] % 10000) + end;
                if (isGood5(next)) {
                    GOOD5_CONNECTED[i][++GOOD5_CONNECTED[i][0]] = GOOD5_IDX[next];
                }
            }
        }
    }

    public static void main(String[] args){
            int[] f = new int[400001];
            f[1] = 9;
            f[2] = 90;
            f[3] = (int) IntStream.range(100, 1000).filter(Sollution11::isGood3).count();
            f[4] = (int) IntStream.range(1000, 10000).filter(Sollution11::isGood4).count();
            int size = GOOD5[0];
            int[] cnt = new int[size + 1];
            Arrays.fill(cnt, 1);
            int[] nextCnt = new int[size + 1];
            for (int n = 5; n <= 400000; n++) {
                for (int i = 1; i <= size; i++) {
                    if (GOOD5[i] >= 10000) {
                        f[n] = (f[n] + cnt[i]) % MOD;
                    }
                    int res = 0;
                    for (int j = 1; j <= GOOD5_CONNECTED[i][0]; j++) {
                        res = (res + cnt[GOOD5_CONNECTED[i][j]]) % MOD;
                    }
                    nextCnt[i] = res;
                }
                int[] tmp = cnt;
                cnt = nextCnt;
                nextCnt = tmp;
            }
        System.out.println(f[6652]);
        }
}
//488577665
