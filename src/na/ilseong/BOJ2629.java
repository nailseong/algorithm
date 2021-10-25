package na.ilseong;

import java.util.*;
import java.io.*;

public class BOJ2629 {

    static int N, M;
    static int[] weights, balls;

    static boolean[][] dp;

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        weights = new int[N];
        dp = new boolean[40_001][N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            weights[i] = Integer.parseInt(st.nextToken());
        }

        solve(0, 0);

        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int num = Integer.parseInt(st.nextToken());
            sb.append(dp[num][N] ? "Y " : "N ");
        }

        System.out.println(sb);
    }

    private static void solve(int index, int weight) {
        if (dp[weight][index]) return;
        dp[weight][index] = true;

        if (index == N) return;

        solve(index + 1, weight + weights[index]);
        solve(index + 1, Math.abs(weight - weights[index]));
        solve(index + 1, weight);

    }

}
