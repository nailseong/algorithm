package na.ilseong;

import java.util.*;
import java.io.*;

public class BOJ11066 {

    static int INF = Integer.MAX_VALUE;

    static int T, K;
    static int[] series;
    static int[][] dp;
    static int[] sum;

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        while (T-- > 0) {

            K = Integer.parseInt(br.readLine());
            series = new int[K];
            dp = new int[K][K];
            sum = new int[K];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < K; i++) {
                series[i] = Integer.parseInt(st.nextToken());
            }

            sum[0] = series[0];
            for (int i = 1; i < K; i++) {
                sum[i] = sum[i - 1] + series[i];
            }

            for (int i = 0; i < K - 1; i++) {
                dp[i][i + 1] = series[i] + series[i + 1];
            }

            for (int len = 2; len < K; len++) {
                for (int i = 0; i < K - len; i++) {

                    dp[i][i + len] = INF;

                    for (int k = i; k < i + len; k++) {
                        dp[i][i + len] = Math.min(dp[i][i + len], dp[i][k] + dp[k + 1][i + len] + calculate(i, i + len));
                    }

                }
            }

            sb.append(dp[0][K - 1] + "\n");
        }

        System.out.println(sb);
    }

    private static int calculate(int start, int end) {

        if (start == 0) return sum[end];

        return sum[end] - sum[start - 1];
    }
}
