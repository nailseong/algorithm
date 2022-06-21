package na.ilseong.BOJ.BOJ1000;

import java.util.*;
import java.io.*;

public class BOJ1014 {

    static int T, N, M;
    static boolean[][] matrix;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            matrix = new boolean[N][M];
            for (int i = 0; i < N; i++) {
                String input = br.readLine();
                for (int j = 0; j < M; j++) {
                    char c = input.charAt(j);
                    if (c == '.') matrix[i][j] = true;
                }
            }

            dp = new int[1 << N][M];
            for (int[] ints : dp) {
                Arrays.fill(ints, -1);
            }

            sb.append(solve(0, 0))
                    .append("\n");
        }

        System.out.println(sb.toString());
    }

    private static int solve(int y, int mask) {

        if (y == M) return 0;
        if (dp[mask][y] != -1) return dp[mask][y];

        int newMask = mask;
        for (int i = 0; i < N; i++) {

            if ((mask & (1 << i)) == 0) continue;

            if (i != 0) newMask |= (1 << i - 1);
            if (i != N - 1) newMask |= (1 << i + 1);

        }

        int tmp = solve(y + 1, 0);
        for (int i = 1; i < (1 << N); i++) { // i : 빈 자리에 학생을 앉힌 mask

            if ((newMask & i) != 0) continue;

            int count = 0;
            boolean flag = false;

            for (int j = 0; j < N; j++) {

                if ((i & (1 << j)) == 0) continue;

                if (!matrix[j][y]) { // 학생이 앉은 자리가 앉을 수 없는 자리였을때
                    flag = true;
                    break;
                }

                count++;
            }

            if (flag) continue;

            tmp = Math.max(tmp, solve(y + 1, i) + count);
        }

        return dp[mask][y] = tmp;
    }
}
