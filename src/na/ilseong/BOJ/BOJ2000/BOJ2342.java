package na.ilseong.BOJ.BOJ2000;

import java.util.*;
import java.io.*;

public class BOJ2342 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int[] series;
    static int[][][] dp;

    static int ANSWER;

    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split(" ");

        series = new int[input.length - 1];
        for (int i = 0; i < series.length; i++) {
            series[i] = Integer.parseInt(input[i]);
        }

        dp = new int[5][5][series.length + 1];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        ANSWER = solve(0, 0, 0);
        System.out.println(ANSWER);
    }

    private static int solve(int left, int right, int idx) {
        if (idx == series.length) {
            return 0;
        }

        if (dp[left][right][idx] != -1) {
            return dp[left][right][idx];
        }

        dp[left][right][idx] = Math.min(
                solve(series[idx], right, idx + 1) + calculate(left, series[idx]), // 왼발 이동
                solve(left, series[idx], idx + 1) + calculate(right, series[idx])); // 오른발 이동

        return dp[left][right][idx];
    }

    private static int calculate(int current, int next) {
        int abs = Math.abs(current - next);
        if (current == 0) {
            return 2;
        } else if (abs == 0) {
            return 1;
        } else if (abs == 1 || abs == 3) {
            return 3;
        } else {
            return 4;
        }
    }
}
