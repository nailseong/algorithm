package na.ilseong;

import java.util.*;
import java.io.*;

public class BOJ2169 {

    static int N, M;
    static int[][] matrix;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dp = new int[N][M];
        matrix = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 0번 행 초기화
        dp[0][0] = matrix[0][0];
        for (int i = 1; i < M; i++) {
            dp[0][i] += dp[0][i - 1] + matrix[0][i];
        }

        // 1 ~ N-2번 행 초기화
        for (int i = 1; i < N; i++) {

            // 좌 -> 우
            int leftToRight[] = new int[M];
            leftToRight[0] = dp[i - 1][0] + matrix[i][0];
            for (int j = 1; j < M; j++) {
                leftToRight[j] = Math.max(leftToRight[j - 1], dp[i - 1][j]) + matrix[i][j];
            }

            // 우 -> 좌
            int rightToLeft[] = new int[M];
            rightToLeft[M - 1] = dp[i - 1][M - 1] + matrix[i][M - 1];
            for (int j = M - 2; j >= 0; j--) {
                rightToLeft[j] = Math.max(rightToLeft[j + 1], dp[i - 1][j]) + matrix[i][j];
            }

            // dp 초기화
            for (int j = 0; j < M; j++) {
                dp[i][j] = Math.max(leftToRight[j], rightToLeft[j]);
            }

        }

        System.out.println(dp[N - 1][M - 1]);
    }

    private static void print() {
        for (int[] ints : dp) {
            for (int i : ints) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

}
