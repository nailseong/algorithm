package na.ilseong.BOJ.BOJ15000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ15654 {

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

        st = new StringTokenizer(br.readLine());
        arr = new int[N];
        visited = new boolean[N];
        series = new int[M];
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
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            series[depth] = arr[i];

            solve(depth + 1);

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
