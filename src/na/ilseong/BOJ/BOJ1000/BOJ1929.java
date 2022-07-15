package na.ilseong.BOJ.BOJ1000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1929 {

    private static boolean[] bools;
    private static BufferedWriter bw;

    public static void main(String[] args) throws Exception {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int from = Integer.parseInt(st.nextToken());
        int to = Integer.parseInt(st.nextToken());

        bools = new boolean[to + 1];
        Arrays.fill(bools, true);
        bools[0] = false; bools[1] = false;

        for (int i = 2; i <= to; i++) {
            int target = i * 2;
            while(target <= to) {
                bools[target] = false;
                target += i;
            }
        }

        for (int i = from; i <= to; i++) {
            if (bools[i]) {
                bw.write(i + "\n");
            }
        }

        bw.flush();
        bw.close();
    }
}
