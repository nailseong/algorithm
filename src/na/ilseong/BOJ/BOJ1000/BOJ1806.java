package na.ilseong.BOJ.BOJ1000;

import java.util.*;
import java.io.*;

public class BOJ1806 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, S;

    static Integer[] series;
    static int LEFT, RIGHT, SUM;

    static int ANSWER = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        series = new Integer[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            series[i] = Integer.parseInt(st.nextToken());
        }

        LEFT = 0;
        RIGHT = 0;
        SUM = 0;

        while (true) {

            if (SUM >= S) {
                SUM -= series[LEFT];
                ANSWER = Math.min(ANSWER, RIGHT - LEFT);
                LEFT++;
            } else if (RIGHT == N) {
                break;
            } else {
                SUM += series[RIGHT];
                RIGHT++;
            }


        }

        System.out.println(ANSWER != Integer.MAX_VALUE ? ANSWER : 0);
    }

}
