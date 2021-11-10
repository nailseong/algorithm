package na.ilseong.BOJ;

import java.util.*;
import java.io.*;

public class BOJ10942 {

    static int N, M;
    static int[] series;

    static boolean[][] dp;

    static StringBuilder ANSWER = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        series = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            series[i] = Integer.parseInt(st.nextToken());
        }

        dp = new boolean[N + 1][N + 1];
        calculate();

//        for (int i = 1; i <= N; i++) {
//            for (int j = 1; j <= N; j++) {
//                System.out.print(dp[i][j] + " ");
//            }
//            System.out.println();
//        }

        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            if (dp[S][E]) ANSWER.append("1\n");
            else ANSWER.append("0\n");
        }

        System.out.print(ANSWER);
    }

    private static void calculate() {

        for (int i = 1; i <= N; i++) {
            dp[i][i] = true;
            if (series[i] == series[i - 1]) {
                dp[i - 1][i] = true;
            }
        }

        for (int i = 2; i < N; i++) { // 길이 (2 ~ N-1)
            for (int j = 1; j <= N - i; j++) { // 앞 인덱스 (1 ~ N-i)
                // 양 끝이 같고, 중간이 팰린드롬이면
                if (series[j] == series[j + i] && dp[j + 1][j + i - 1]) {
                    dp[j][j + i] = true;
                }
            }
        }

    }

}
