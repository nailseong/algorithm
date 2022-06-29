package na.ilseong.BOJ.BOJ15000;

import java.io.*;
import java.util.*;

public class BOJ15655 {

    private static BufferedWriter bw;

    private static int N;
    private static int M;
    private static int[] arr;
    private static boolean[] visited;

    private static int[] series;

    public static void main(String[] args) throws Exception {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        visited = new boolean[N];
        series = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        solve(-1, 0);

        bw.flush();
        bw.close();
    }

    private static void solve(int preIdx, int depth) throws Exception {
        if (depth == M) {
            print();
            return;
        }
        for (int i = preIdx + 1; i < N; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            series[depth] = arr[i];

            solve(i, depth + 1);

            visited[i] = false;
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
