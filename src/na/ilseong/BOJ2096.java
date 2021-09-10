package na.ilseong;

import java.util.*;
import java.io.*;

public class BOJ2096 {

    static int N;

    static int[][] matrix;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        matrix = new int[N][3];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            matrix[i][0] = Integer.parseInt(st.nextToken());
            matrix[i][1] = Integer.parseInt(st.nextToken());
            matrix[i][2] = Integer.parseInt(st.nextToken());
        }

        dp = new int[N][3];
        dp[0][0] = matrix[0][0];
        dp[0][1] = matrix[0][1];
        dp[0][2] = matrix[0][2];


        System.out.println(getMax() + " " + getMin());

        for (int[] ints : dp) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }


    }

    private static int getMax() {
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < 3; j++) {
                if (j == 0) {
                    dp[i][j] = matrix[i][j] + Math.max(dp[i - 1][j], dp[i - 1][j + 1]);
                } else if (j == 1) {
                    dp[i][j] = matrix[i][j] + Arrays.stream(dp[i - 1]).max().getAsInt();
                } else {
                    dp[i][j] = matrix[i][j] + Math.max(dp[i - 1][j], dp[i - 1][j - 1]);
                }
            }
        }

        return Arrays.stream(dp[N - 1]).max().getAsInt();
    }

    private static int getMin() {
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < 3; j++) {
                if (j == 0) {
                    dp[i][j] = matrix[i][j] + Math.min(dp[i - 1][j], dp[i - 1][j + 1]);
                } else if (j == 1) {
                    dp[i][j] = matrix[i][j] + Arrays.stream(dp[i - 1]).min().getAsInt();
                } else {
                    dp[i][j] = matrix[i][j] + Math.min(dp[i - 1][j], dp[i - 1][j - 1]);
                }
            }
        }

        return Arrays.stream(dp[N - 1]).min().getAsInt();
    }

}
