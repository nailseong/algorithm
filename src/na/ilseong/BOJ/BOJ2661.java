package na.ilseong.BOJ;

import java.util.*;
import java.io.*;

public class BOJ2661 {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        solve("");
    }

    private static void solve(String s) {

        if (s.length() == N) {
            System.out.println(s);
            System.exit(0);
        }

        for (int i = 1; i <= 3; i++) {
            if (isValid(s + i)) {
                solve(s + i);
            }
        }

    }

    private static boolean isValid(String s) {

        int len = s.length();
        for (int i = 1; i <= len / 2; i++) {

            String s1 = s.substring(len - (2 * i), len - i);
            String s2 = s.substring(len - i);

            if (s1.equals(s2)) return false;
        }

        return true;
    }

}
