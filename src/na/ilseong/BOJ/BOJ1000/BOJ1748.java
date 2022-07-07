package na.ilseong.BOJ.BOJ1000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ1748 {
    private static BufferedWriter bw;
    private static int n;

    public static void main(String[] args) throws Exception {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        int current = 0;
        int answer = 0;

        while (current < n) {
            current++;
            if (current < 10) {
                answer += 1;
                continue;
            }
            if (current < 100) {
                answer += 2;
                continue;
            }
            if (current < 1_000) {
                answer += 3;
                continue;
            }
            if (current < 10_000) {
                answer += 4;
                continue;
            }
            if (current < 100_000) {
                answer += 5;
                continue;
            }
            if (current < 1_000_000) {
                answer += 6;
                continue;
            }
            if (current < 10_000_000) {
                answer += 7;
                continue;
            }
            if (current < 100_000_000) {
                answer += 8;
                continue;
            }
        }

        bw.write(Integer.toString(answer));

        bw.flush();
        bw.close();
    }
}
