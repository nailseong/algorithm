package na.ilseong;

import java.util.*;
import java.io.*;

public class BOJ2533 {

    static int N;
    static ArrayList<ArrayList<Integer>> graph;

    static int[][] dp;

    static int ANSWER = 1_000_001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        dp = new int[N + 1][2];
        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        dfs(1, -1);

        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }

    // dp[i][0] -> i 가 얼리어답터가 아님
    // dp[i][1] -> i 가 얼리어답터임
    private static void dfs(int current, int parent) {

        dp[current][0] = 0;
        dp[current][1] = 1;

        for (Integer child : graph.get(current)) {
            if (child != parent) {
                dfs(child, current);
                dp[current][0] += dp[child][1];
                dp[current][1] += Math.min(dp[child][0], dp[child][1]);
            }
        }

    }

}
