package na.ilseong.BOJ;

import java.util.*;
import java.io.*;

public class BOJ12738 {

    static int N;
    static int[] series;

    static int[] dp;

    static int INF = 1_000_000_001;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        series = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            series[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[N + 1];
        Arrays.fill(dp, INF);
        dp[0] = -INF;

        for (int i = 1; i <= N; i++) {
            int value = series[i];
            int idx = binarySearch(value);
            dp[idx] = value;
        }

        int ans = 0;
        for (int i = 1; i <= N; i++) {
            if (dp[i] < INF) {
                ans++;
            }
        }

        System.out.println(ans);
    }

    private static int binarySearch(int value) {
        int left = 0;
        int right = N;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (dp[mid] >= value) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

}
