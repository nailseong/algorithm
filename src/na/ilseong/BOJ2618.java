package na.ilseong;

import java.util.*;
import java.io.*;

public class BOJ2618 {

    static int N, W;
    static int[][] series;

    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        W = Integer.parseInt(br.readLine());


        series = new int[W + 1][2];
        for (int i = 1; i <= W; i++) {
            st = new StringTokenizer(br.readLine());
            series[i][0] = Integer.parseInt(st.nextToken());
            series[i][1] = Integer.parseInt(st.nextToken());
        }

        dp = new int[W + 1][W + 1];

        StringBuilder sb = new StringBuilder();
        sb.append(solve(0, 0, 1) + "\n");

        int indexFirst = 0;
        int indexSecond = 0;
        for (int i = 1; i <= W; i++) {

            int dist = calculate(true, indexFirst, i);

            if (dp[indexFirst][indexSecond] == dp[i][indexSecond] + dist) {
                indexFirst = i;
                sb.append("1\n");
            } else {
                indexSecond = i;
                sb.append("2\n");
            }

        }

        System.out.println(sb);
    }

    private static int solve(int first, int second, int count) {

        if (count > W) return 0;

        if (dp[first][second] != 0) return dp[first][second];

        // 첫 번째 경찰차 이동
        int tmp1 = solve(count, second, count + 1) + calculate(true, first, count);

        // 두 번째 경찰차 이동
        int tmp2 = solve(first, count, count + 1) + calculate(false, second, count);

        return dp[first][second] = Math.min(tmp1, tmp2);
    }


    private static int calculate(boolean isFirst, int a, int b) {

        if (a == 0) {
            if (isFirst) return (series[b][0] - 1) + (series[b][1] - 1);
            return (N - series[b][0]) + (N - series[b][1]);
        }

        return Math.abs(series[a][0] - series[b][0]) + Math.abs(series[a][1] - series[b][1]);
    }

}
