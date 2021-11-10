package na.ilseong.BOJ;

import java.util.*;
import java.io.*;

public class BOJ2812 {

    static int N, K;
    static int[] series;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        series = new int[N];
        String input = br.readLine();
        for (int i = 0; i < N; i++) {
            series[i] = Integer.parseInt(String.valueOf(input.charAt(i) - '0'));
        }

        solve();
    }

    private static void solve() {

        int count = 0;
        int index = 0;
        Deque<Integer> deque = new ArrayDeque<>();

        while (count < K && index < N) {

            if (deque.isEmpty()) {
                deque.addLast(series[index++]);
                continue;
            }

            if (deque.peekLast() < series[index]) {
                deque.pollLast();
                count++;
                continue;
            }

            deque.addLast(series[index++]);

        }

        while (index < N) {
            deque.addLast(series[index++]);
        }

        StringBuilder sb = new StringBuilder();
        int tmp = 0;
        while (tmp++ < (N - K)) {
            sb.append(deque.pollFirst());
        }

        System.out.println(sb);
    }

}
