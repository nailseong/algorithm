package na.ilseong.BOJ.BOJ15000;

import java.util.*;
import java.io.*;

public class BOJ15652 {

    private static BufferedWriter bw;

    private static int N;
    private static int M;

    private static int[] arr;
    private static int[] numbers;

    public static void main(String[] args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        for (int i = 1; i <= N; i++) {
            arr[i - 1] = i;
        }
        numbers = new int[M];

        solve(0, 0);

        bw.flush();
        bw.close();
    }

    private static void solve(int pre, int depth) throws IOException {
        if (depth == M) {
            print();
            return;
        }
        for (int i = 0; i < N; i++) {
            if (arr[i] < pre) {
                continue;
            }
            numbers[depth] = arr[i];
            solve(arr[i], depth + 1);

            numbers[depth] = 0;
        }
    }

    private static void print() throws IOException {
        for (int n : numbers) {
            bw.write(n + " ");
        }
        bw.write("\n");
    }
}
