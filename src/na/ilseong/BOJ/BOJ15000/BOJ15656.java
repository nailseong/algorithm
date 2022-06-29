package na.ilseong.BOJ.BOJ15000;

import java.util.*;
import java.io.*;

public class BOJ15656 {

    private static BufferedWriter bw;

    private static int N;
    private static int M;
    private static int[] arr;

    private static int[] series;

    public static void main(String[] args) throws Exception {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        series = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        solve(0);

        bw.flush();
        bw.close();
    }

    private static void solve(int depth) throws Exception {
        if (depth == M) {
            print();
            return;
        }
        for (int i = 0; i < N; i++) {
            series[depth] = arr[i];
            solve(depth + 1);
            series[depth] = 0;
        }
    }

    private static void print() throws Exception {
        for (int n : series) {
            bw.write(n + " ");
        }
        bw.write("\n");
    }
}
