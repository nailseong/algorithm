package na.ilseong.BOJ.BOJ14000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ14501 {

    private static BufferedWriter bw;
    private static int N;
    private static int[] nums;
    private static int[] values;
    private static int answer = -1;
    private static boolean[] ables;

    public static void main(String[] args) throws Exception {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        nums = new int[N];
        values = new int[N];
        ables = new boolean[N];
        Arrays.fill(ables, true);

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            nums[i] = Integer.parseInt(st.nextToken());
            values[i] = Integer.parseInt(st.nextToken());
        }

        solve(0, 0);

        bw.write(answer + "");
        bw.flush();
        bw.close();
    }

    private static void solve(int idx, int sum) {
        if (idx >= N) {
            if (sum > answer) {
                answer = sum;
            }
            return;
        }
        for (int i = idx; i < N; i++) {
            if (i + nums[i] > N) {
                solve(i + nums[i], sum);
                continue;
            }
            solve(i + nums[i], sum + values[i]);
        }
    }
}
