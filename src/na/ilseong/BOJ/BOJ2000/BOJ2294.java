package na.ilseong.BOJ.BOJ2000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ2294 {

    private static final int MAX_VALUE = 1_000_001;

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        final int n = Integer.parseInt(st.nextToken());
        final int k = Integer.parseInt(st.nextToken());

        final List<Integer> coins = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            final int coinValue = Integer.parseInt(st.nextToken());
            coins.add(coinValue);
        }

        final int[] dp = new int[k + 1];
        for (int i = 1; i <= k; i++) {
            dp[i] = MAX_VALUE;
        }

        initDp(dp, coins, k);
        if (dp[k] == MAX_VALUE) {
            System.out.println(-1);
            return;
        }
        System.out.println(dp[k]);
    }

    private static void initDp(int[] dp, List<Integer> coins, int targetIndex) {
        for (int i = 0; i <= targetIndex; i++) {
            int currentValue = dp[i];
            for (Integer coin : coins) {
                final int previousIndex = i - coin;
                if (previousIndex < 0) {
                    continue;
                }
                if (dp[previousIndex] + 1 < currentValue) {
                    currentValue = dp[previousIndex] + 1;
                }
            }
            dp[i] = currentValue;
        }
    }
}
