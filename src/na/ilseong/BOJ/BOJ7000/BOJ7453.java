package na.ilseong.BOJ.BOJ7000;

import java.util.*;
import java.io.*;

public class BOJ7453 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N;
    static int[][] series;

    static int[] seriesAB;
    static int[] seriesCD;
    
    static long ANSWER = 0;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        series = new int[4][N];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                series[j][i] = Integer.parseInt(st.nextToken());
            }
        }

        seriesAB = new int[N * N];
        seriesCD = new int[N * N];

        int idx = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                seriesAB[idx] = series[0][i] + series[1][j];
                seriesCD[idx] = series[2][i] + series[3][j];
                idx++;
            }
        }

        Arrays.sort(seriesCD);

        for (int target : seriesAB) {

            int upper = binarySearch_upper(-target);
            int lower = binarySearch_lower(-target);

            ANSWER += (upper - lower);

        }

        System.out.println(ANSWER);
    }

    private static int binarySearch_upper(int target) {

        int left = 0;
        int right = N * N;

        while (left < right) {

            int mid = (left + right) / 2;

            if (seriesCD[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }

        }

        return left;
    }

    private static int binarySearch_lower(int target) {

        int left = 0;
        int right = N * N;

        while (left < right) {

            int mid = (left + right) / 2;

            if (seriesCD[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }

        }

        return left;
    }

}
