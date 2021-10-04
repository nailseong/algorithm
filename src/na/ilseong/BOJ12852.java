package na.ilseong;

import java.util.*;
import java.io.*;

public class BOJ12852 {

    static int N;

    static Node[] dp;
    static int cnt;
    static int parent;

    static int INF = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        dp = new Node[N + 1];
        dp[1] = new Node(0, "1");

        for (int i = 2; i <= N; i++) {

            cnt = INF;
            parent = 0;

            if (i % 3 == 0) {
                cnt = dp[i / 3].count;
                parent = i / 3;
            }

            if (i % 2 == 0 && cnt > dp[i / 2].count) {
                cnt = dp[i / 2].count;
                parent = i / 2;
            }

            if (cnt > dp[i - 1].count) {
                parent = i - 1;
            }

            dp[i] = new Node(dp[parent].count + 1, i + " " + dp[parent].history);
        }

        System.out.println(dp[N].count);
        System.out.println(dp[N].history);
    }

    private static class Node {
        int count;
        String history;

        public Node(int count, String history) {
            this.count = count;
            this.history = history;
        }
    }

}
