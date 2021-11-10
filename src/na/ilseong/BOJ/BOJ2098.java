package na.ilseong.BOJ;

import java.util.*;
import java.io.*;

// 비트마스킹, dp, 외판원 순환 문제 (tsp)
public class BOJ2098 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int INF = Integer.MAX_VALUE - 1_000_000;

    static int N;
    static int[][] matrix = new int[16][16];
    static int[][] dp = new int[16][1 << 16];

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(tsp(0, 1));
    }

    private static int tsp(int current, int visited) {
        // 방문 -완-
        if (visited == (1 << N) - 1) {
            return matrix[current][0] != 0
                    ? matrix[current][0]
                    : INF;
        }

        // 이미 방문 했음
        if (dp[current][visited] != 0) {
            return dp[current][visited];
        }

        // 방문 처리
        dp[current][visited] = INF;
        for (int i = 0; i < N; i++) {

            int next = 1 << i;

            if (matrix[current][i] == 0 || (visited & next) > 0) {
                continue;
            }

            dp[current][visited] = Math.min(
                    dp[current][visited],
                    tsp(i, visited | next) + matrix[current][i]
            );

        }
        
        return dp[current][visited];
    }

}