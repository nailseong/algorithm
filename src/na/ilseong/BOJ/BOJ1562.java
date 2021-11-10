package na.ilseong.BOJ;

import java.util.*;
import java.io.*;

public class BOJ1562 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N;
    static int[][][] dp;

    static int NUM = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        // dp[자릿수][끝자리][비트마스킹]
        dp = new int[N + 1][10][1 << 10];
        for (int i = 1; i < 10; i++) {
            dp[1][i][1 << i] = 1;
        }

        for (int i = 2; i < N + 1; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < (1 << 10); k++) {
                    int bit = k | (1 << j);
                    if (j == 0) {
                        dp[i][0][bit] = (dp[i][0][bit] + dp[i - 1][1][k]) % NUM;
                    } else if (j == 9) {
                        dp[i][9][bit] = (dp[i][9][bit] + dp[i - 1][8][k]) % NUM;
                    } else {
                        dp[i][j][bit] = (dp[i][j][bit] + dp[i - 1][j + 1][k] + dp[i - 1][j - 1][k]) % NUM;
                    }
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < 10; i++) {
            answer = (answer + dp[N][i][(1 << 10) - 1]) % NUM;
        }
        System.out.println(answer);
    }

}
