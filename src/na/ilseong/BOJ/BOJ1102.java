package na.ilseong.BOJ;

import java.util.*;
import java.io.*;

public class BOJ1102 {

    static int N, P;
    static int[][] matrix;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        matrix = new int[N][N];
        dp = new int[N][1 << N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }

            Arrays.fill(dp[i], -1); // 비용이 0 이상이므로 -1 로 초기화한다
        }

        String input = br.readLine();
        P = Integer.parseInt(br.readLine());

        int count = 0;
        int bitmask = 0;
        for (int i = 0; i < N; i++) {
            if (input.charAt(i) == 'Y') {
                count++;
                bitmask = bitmask | (1 << i);
            }
        }

        System.out.println(dfs(count, bitmask) != Integer.MAX_VALUE
                            ? dfs(count, bitmask)
                            : -1
        );

    }

    private static int dfs(int count, int bitmask) {

        if (count >= P) return 0;

        if (dp[count][bitmask] != -1) return dp[count][bitmask];

        dp[count][bitmask] = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {

            // i 번 발전소가 이미 켜져있음
            if ((bitmask & (1 << i)) != 0) continue;

            for (int j = 0; j < N; j++) {

                if ((bitmask & (1 << j)) == 0) continue;

                // j 번 발전소로 i 번 발전소 켜기
                dp[count][bitmask] = Math.min(
                        dp[count][bitmask],
                        dfs(count + 1, bitmask | (1 << i)) + matrix[j][i]
                );

            }

        }

        return dp[count][bitmask];
    }

}
