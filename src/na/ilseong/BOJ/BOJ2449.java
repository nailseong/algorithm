package na.ilseong.BOJ;

import java.util.*;
import java.io.*;

public class BOJ2449 {

    static int N, K;
    static int[] series;

    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        series = new int[N];
        dp = new int[N][N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            series[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solve(0, N - 1));
    }

    /*
    dp[i][j] = i ~ j 구간을 같은 색으로 바꾸는 최소 횟수
    */
    private static int solve(int start, int end) {

        if (start == end) return 0;

        if (dp[start][end] != 0) return dp[start][end];

        int tmp = Integer.MAX_VALUE;
        for (int i = start; i < end; i++) {

            int num = solve(start, i) + solve(i + 1, end);

            // EX) 2, 3, 3, 3, 2
            //     -> 2, 3, 3, 3 / 2
            //     -> 1
            if (series[start] != series[i + 1]) num++;

            tmp = Math.min(tmp, num);
        }

        return dp[start][end] = tmp;
    }

}