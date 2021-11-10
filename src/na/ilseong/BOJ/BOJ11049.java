package na.ilseong.BOJ;

import java.util.*;
import java.io.*;

public class BOJ11049 {

    static int INF = Integer.MAX_VALUE;

    static int N;
    static Matrix[] list;

    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        list = new Matrix[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            list[i] = new Matrix(r, c);
        }

        dp = new int[N][N];
        for (int i = 0; i < N - 1; i++) {
            dp[i][i + 1] = list[i].x * list[i].y * list[i + 1].y;
        }

        for (int length = 2; length <= N; length++) {
            for (int i = 0; i < N - length; i++) {

                int j = i + length;
                dp[i][j] = INF;

                for (int k = i; k < j; k++) {
                    dp[i][j] = Math.min(
                            dp[i][j],
                            dp[i][k] + dp[k + 1][j] + (list[i].x * list[k].y * list[j].y)
                    );
                }

            }
        }

        System.out.println(dp[0][N - 1]);
    }

    private static class Matrix {
        int x, y;

        public Matrix(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
