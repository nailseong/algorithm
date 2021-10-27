package na.ilseong;

import java.util.*;
import java.io.*;

public class BOJ11062 {

    static int T, N;
    static int[] series;

    static int[][][] dp;

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        while (T-- > 0) {

            N = Integer.parseInt(br.readLine());
            series = new int[N + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                series[i] = Integer.parseInt(st.nextToken());
            }

            dp = new int[2][N + 1][N + 1];
            for (int[][] ints : dp) {
                for (int[] list : ints) {
                    Arrays.fill(list, -1);
                }
            }

            sb.append(solve(0, 1, N) + "\n");

        }

        System.out.println(sb);
    }

    private static int solve(int turn, int start, int end) {

        if (dp[turn][start][end] != -1) return dp[turn][start][end];

        if (start >= end) {
            if (turn == 0) return series[start];
            else return 0;
        }

        dp[turn][start][end] = 0;

        if (turn == 0) {
            dp[turn][start][end] = Math.max(solve(turn + 1, start + 1, end) + series[start],
                    solve(turn + 1, start, end - 1) + series[end]);
        } else {
            dp[turn][start][end] = Math.min(solve(turn - 1, start + 1, end),
                    solve(turn - 1, start, end - 1));
        }

        return dp[turn][start][end];
    }

}
