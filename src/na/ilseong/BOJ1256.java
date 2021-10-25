package na.ilseong;

import java.util.*;
import java.io.*;

public class BOJ1256 {

    static int N, M, K;
    static double[][] dp;

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dp = new double[N + 1][M + 1];
        for (int i = 0; i < N + 1; i++) {
            for (int j = 0; j < M + 1; j++) {
                if (i == 0 || j == 0) dp[i][j] = 1;
                else dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        dp[0][0] = 0;

        if (dp[N][M] < K) {
            System.out.println(-1);
            return;
        }

        solve(N, M);

        System.out.println(sb);
    }

    private static void solve(int a, int z) {
        if (a == 0) {
            while (z-- > 0) {
                sb.append("z");
            }
            return;
        }

        if (z == 0) {
            while (a-- > 0) {
                sb.append("a");
            }
            return;
        }

        double num = dp[a - 1][z];
        if (num >= K) {
            sb.append("a");
            solve(a - 1, z);
        } else {
            sb.append("z");
            K -= num;
            solve(a, z - 1);
        }
    }

}
