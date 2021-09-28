package na.ilseong;

import java.util.*;
import java.io.*;

public class BOJ2473 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N;

    static long[] series;

    static long maxValue = Long.MAX_VALUE;
    static long[] ANSWER = new long[3];

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        series = new long[N];
        for (int i = 0; i < N; i++) {
            series[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(series);
        for (int i = 0; i <= N - 1; i++) {
            calculate(i);
        }

        Arrays.sort(ANSWER);
        for (int i = 0; i < 3; i++) {
            System.out.print(ANSWER[i] + " ");
        }
    }

    private static void calculate(int idx) {
        int left = idx + 1;
        int right = N - 1;

        while (left < right) {
            long tmp = series[idx] + series[left] + series[right];

            if (tmp == 0) {
                ANSWER[0] = series[idx];
                ANSWER[1] = series[left];
                ANSWER[2] = series[right];
                break;
            }

            if (Math.abs(tmp) < maxValue) {
                maxValue = Math.abs(tmp);
                ANSWER[0] = series[idx];
                ANSWER[1] = series[left];
                ANSWER[2] = series[right];
            }

            if (tmp > 0) right -= 1;
            else left += 1;
        }

    }

}
