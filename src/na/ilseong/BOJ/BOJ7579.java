package na.ilseong.BOJ;

import java.util.*;
import java.io.*;

public class BOJ7579 {

    static int N, M;
    static int[] MEMORY;
    static int[] COST;

    static int[][] dp;

    static int ANSWER = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        MEMORY = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            MEMORY[i] = Integer.parseInt(st.nextToken());
        }

        COST = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            COST[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[N][10_001];
        for (int i = 0; i < N; i++) {

            int memory = MEMORY[i];
            int cost = COST[i];

            for (int j = 0; j <= 10_000; j++) {

                if (i == 0) {
                    if (cost <= j) {
                        dp[i][j] = memory;
                    }
                } else {
                    if (cost <= j) {
                        dp[i][j] = Math.max(dp[i - 1][j - cost] + memory, dp[i - 1][j]);
                    } else {
                        dp[i][j] = dp[i - 1][j];
                    }
                }

                if (dp[i][j] >= M) {
                    ANSWER = Math.min(ANSWER, j);
                }
            }

        }

        System.out.println(ANSWER);
    }

}
