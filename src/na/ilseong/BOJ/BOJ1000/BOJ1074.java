package na.ilseong.BOJ.BOJ1000;

import java.util.*;
import java.io.*;

public class BOJ1074 {

    static int N, R, C;

    static int ANSWER = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        solve((int) Math.pow(2, N), 0, 0);

    }

    private static void solve(int n, int r, int c) {

        if (r == R && c == C) {
            System.out.println(ANSWER);
            return;
        }

        // 1사분면
        if (r <= R && R < r + n / 2 && c + n / 2 <= C && C < c + n) {
            ANSWER += Math.pow(n, 2) / 4;
            solve(n / 2, r, c + n / 2);
        }

        // 2사분면
        if (r <= R && R < r + n / 2 && c <= C && C < c + n / 2) {
            solve(n / 2, r, c);
        }

        // 3사분면
        if (r + n / 2 <= R && R < r + n && c <= C && C < c + n / 2) {
            ANSWER += Math.pow(n, 2) / 2;
            solve(n / 2, r + n / 2, c);
        }

        // 4사분면
        if (r + n / 2 <= R && R < r + n && c + n / 2 <= C && C < c + n) {
            ANSWER += Math.pow(n, 2) / 4 * 3;
            solve(n / 2, r + n / 2, c + n / 2);
        }

    }

}
