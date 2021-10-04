package na.ilseong;

import java.util.*;
import java.io.*;

public class BOJ14002 {

    static int N;
    static int[] series;

    static int[] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        series = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            series[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[N + 1];

        int lastIndex = 0;
        for (int i = 1; i <= N; i++) {
            int value = series[i];
            for (int j = 0; j < i; j++) {
                if (series[j] < value) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    lastIndex = Math.max(lastIndex, dp[i]);
                }
            }
        }

        System.out.println(lastIndex);

        StringBuilder ans = new StringBuilder();
        for (int i = N; i > 0; i--) {
            if (dp[i] == lastIndex) {
                ans.insert(0, series[i] + " ");
                lastIndex--;
            }
        }

        System.out.println(ans);
    }
}
