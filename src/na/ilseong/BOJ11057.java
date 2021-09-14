package na.ilseong;

import java.util.*;
import java.io.*;

public class BOJ11057 {

    static int N;
    static int[][] dp;

    static int ANSWER;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        dp = new int[N + 1][10];
        Arrays.fill(dp[1], 1);

        for (int i = 2; i <= N; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = j; k < 10; k++) {
                    dp[i][j] += dp[i - 1][k];
                    dp[i][j] %= 10_007;
                }
            }
        }

        for (int a : dp[N]) {
            ANSWER += a;
        }

        System.out.println(ANSWER % 10_007);
    }

}
