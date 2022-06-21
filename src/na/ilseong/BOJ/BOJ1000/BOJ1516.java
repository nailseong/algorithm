package na.ilseong.BOJ.BOJ1000;

import java.util.*;
import java.io.*;

public class BOJ1516 {

    static int N;
    static int[] series;
    static ArrayList<ArrayList<Integer>> orders = new ArrayList<>();

    static int[] dp;

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        series = new int[N + 1];
        dp = new int[N + 1];

        for (int i = 0; i <= N + 1; i++) {
            orders.add(new ArrayList<>());
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            series[i] = Integer.parseInt(st.nextToken());

            while (true) {
                int num = Integer.parseInt(st.nextToken());

                if (num == -1) break;

                orders.get(i).add(num);

            }

        }

        for (int i = 1; i <= N; i++) {

            solve(i);

            sb.append(dp[i] + "\n");
        }

        System.out.println(sb);
    }

    private static int solve(int index) {

        if (dp[index] != 0) return dp[index];

        if (orders.get(index).size() == 0) {
            dp[index] = series[index];
            return dp[index];
        }

        int tmp = 0;
        for (Integer integer : orders.get(index)) {
            tmp = Math.max(solve(integer), tmp);
        }

        dp[index] = tmp + series[index];

        return dp[index];
    }

}
