package na.ilseong.BOJ.BOJ9000;

import java.util.*;
import java.io.*;

public class BOJ9252 {

    static char[] a;
    static char[] b;

    static int[][] dp;
    static String[][] history;

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
        history = new String[a.length + 1][b.length + 1];
        for (int i = 0; i < a.length; i++) {
            Arrays.fill(history[i], "");
        }

        for (int i = 1; i <= a.length; i++) {
            for (int j = 1; j <= b.length; j++) {

                if (a[i - 1] == b[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    history[i][j] = history[i - 1][j - 1] + a[i - 1];
                } else {
                    if (dp[i - 1][j] > dp[i][j - 1]) {
                        dp[i][j] = dp[i - 1][j];
                        history[i][j] = history[i - 1][j];
                    } else {
                        dp[i][j] = dp[i][j - 1];
                        history[i][j] = history[i][j - 1];
                    }
                }

            }
        }

        if (dp[a.length][b.length] != 0) {
            System.out.println(dp[a.length][b.length]);
            System.out.println(history[a.length][b.length]);
        } else {
            System.out.println("0");
        }
    }

}
