package na.ilseong.BOJ.BOJ2000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2309 {
    private static int[] arr = new int[9];
    private static boolean[] visited = new boolean[9];
    private static BufferedWriter bw;

    public static void main(String[] args) throws Exception {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for(int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        solve(0, 0);

        bw.flush();
        bw.close();
    }

    private static void solve(int idx, int count) throws Exception {
        if (count == 2) {
            sum();
            return;
        }
        for (int i = idx; i < 9; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            solve(idx + 1, count + 1);
            visited[i] = false;
        }
    }

    private static void sum() throws Exception {
        int num = 0;
        for (int i = 0; i < 9; i++) {
            if (visited[i]) {
                continue;
            }
            num += arr[i];
        }
        if (num == 100) {
            print();
        }
    }

    private static void print() throws Exception {
        for (int i = 0; i < 9; i++) {
            if (visited[i]) {
                continue;
            }
            bw.write(arr[i] + "\n");
        }
    }
}
