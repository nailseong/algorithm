package na.ilseong.BOJ;

import java.util.*;
import java.io.*;

public class BOJ9251 {

    static char[] a, b;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;

        input = br.readLine();
        a = new char[input.length()];
        for (int i = 0; i < input.length(); i++) {
            a[i] = input.charAt(i);
        }

        input = br.readLine();
        b = new char[input.length()];
        for (int i = 0; i < input.length(); i++) {
            b[i] = input.charAt(i);
        }

        dp = new int[a.length + 1][b.length + 1];
        for (int i = 1; i <= a.length; i++) {
            for (int j = 1; j <= b.length; j++) {

                if (a[i - 1] == b[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }

            }
        }

        System.out.println(dp[a.length][b.length]);
    }

}
