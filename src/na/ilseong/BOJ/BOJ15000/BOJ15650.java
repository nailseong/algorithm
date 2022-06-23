package na.ilseong.BOJ.BOJ15000;

import java.util.*;
import java.io.*;

public class BOJ15650 {

    private static int N;
    private static int M;
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        for (int i = 1; i <= N; i++) {
            arr[i - 1] = i;
        }
        boolean[] visited = new boolean[N];
        int[] numbers = new int[M];

        solve(visited, numbers, 0, 0);
    }

    private static void solve(boolean[] visited, int[] numbers, int depth, int pre) {
        if (depth == M) {
            print(numbers);
            return;
        }
        for (int i = pre; i < N; i++) {
            if (visited[i] == true) {
                continue;
            }
            visited[i] = true;
            numbers[depth] = arr[i];
            solve(visited, numbers, depth + 1, arr[i]);

            visited[i] = false;
            numbers[depth] = 0;
        }
    }

    private static void print(int[] numbers) {
        for (int n : numbers) {
            System.out.print(n + " ");
        }
        System.out.println();
    }
}