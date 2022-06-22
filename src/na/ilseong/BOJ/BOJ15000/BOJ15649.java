package na.ilseong.BOJ.BOJ15000;

import java.io.*;
import java.util.*;

public class BOJ15649 {

    private static int N;
    private static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        for (int i = 1; i <= N; i++) {
            arr[i - 1] = i;
        }
        int[] output = new int[N];
        boolean[] visited = new boolean[N];

        perm(arr, output, visited, 0);
    }

    private static void perm(int[] arr, int[] output, boolean[] visited, int depth) {
        if (depth == M) {
            print(output);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (visited[i] != true) {
                visited[i] = true;
                output[depth] = arr[i];
                perm(arr, output, visited, depth + 1);
                output[depth] = 0;
                visited[i] = false;
            }
        }
    }

    private static void print(int[] arr) {
        for (int i = 0; i < M; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
