package na.ilseong.BOJ;

import java.util.*;
import java.io.*;

public class BOJ2166 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N;
    static long[] xList;
    static long[] yList;

    static long ANSWER;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        xList = new long[N + 1];
        yList = new long[N + 1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            long x = Integer.parseInt(st.nextToken());
            long y = Integer.parseInt(st.nextToken());

            xList[i] = x;
            yList[i] = y;
        }

        xList[N] = xList[0];
        yList[N] = yList[0];

        long left = 0;
        long right = 0;
        for (int i = 0; i < N; i++) {
            left += (xList[i] * yList[i + 1]);
            right += (yList[i] * xList[i + 1]);
        }

        ANSWER = Math.abs(left - right);
        System.out.printf("%.1f\n", (double) ANSWER / 2);
    }

}
