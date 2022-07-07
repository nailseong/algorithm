package na.ilseong.BOJ.BOJ9000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ9095 {

    private static int[] numbers = {1, 2, 3};

    public static void main(String[] args) throws Exception {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());
        while(t-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int[] dp = new int[n + 1];
            Arrays.fill(dp, 0);
            if (n >= 1) {
                dp[1] = 1;
            }
            if (n >= 2) {
                dp[2] = 2;
            }
            if (n >= 3) {
                dp[3] = 4;
            }
            solve(dp);

            bw.write(dp[n] + "\n");
        }
        bw.flush();
        bw.close();
    }

    private static void solve(int[] dp) {
        for (int i = 4; i < dp.length; i++) {
            for (int num : numbers) {
                if (i - num < 1) {
                    continue;
                }
                dp[i] += dp[i - num];
            }
        }
    }
}
