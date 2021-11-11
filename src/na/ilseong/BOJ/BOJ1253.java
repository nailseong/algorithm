package na.ilseong.BOJ;

import java.util.*;
import java.io.*;

public class BOJ1253 {

    static int N;
    static int[] series;

    static int ANSWER = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        series = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            series[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(series);

        for (int i = 0; i < N; i++) {
            search(0, N - 1, i);
        }

        System.out.println(ANSWER);
    }

    private static void search(int left, int right, int index) {

        int target = series[index];

        while (left < right) {
            if (left == index) {
                left++;
                continue;
            }
            if (right == index) {
                right--;
                continue;
            }

            if (series[left] + series[right] == target) {
                ANSWER++;
                break;
            }

            if (series[left] + series[right] < target) left++;
            else right--;

        }

    }

}
