package na.ilseong.BOJ;

import java.util.*;
import java.io.*;

public class BOJ11054 {

    static int N;
    static int[] series;

    static int[] leftDp;
    static int[] rightDP;

    static int ANSWER = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        series = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            series[i] = Integer.parseInt(st.nextToken());
        }

        leftDp = new int[N];
        // index 오름차순 LIS
        for (int current = 0; current < N; current++) {

            leftDp[current] = 1;

            for (int i = 0; i < current; i++) {
                if (series[i] < series[current]) {
                    leftDp[current] = Math.max(leftDp[current], leftDp[i] + 1);
                }
            }

        }

        rightDP = new int[N];
        // index 내림차순 LIS
        for (int current = N - 1; current >= 0; current--) {

            rightDP[current] = 1;

            for (int i = N - 1; i > current; i--) {
                if (series[i] < series[current]) {
                    rightDP[current] = Math.max(rightDP[current], rightDP[i] + 1);
                }
            }

        }


        for (int i = 0; i < N; i++) {
            ANSWER = Math.max(ANSWER, leftDp[i] + rightDP[i] - 1);
        }
        System.out.println(ANSWER);
    }
}
