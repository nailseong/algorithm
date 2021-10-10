package na.ilseong;

import java.util.*;
import java.io.*;

public class BOJ2042 {

    static int N, M, K;
    static long[] series;
    static long[] tree;

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        series = new long[N + 1];
        tree = new long[N * 4];
        for (int i = 1; i <= N; i++) {
            series[i] = Long.parseLong(br.readLine());
        }

        init(1, N, 1);

        for (int i = 0; i < M + K; i++) {

            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());

            if (command == 1) { // update
                int a = Integer.parseInt(st.nextToken());
                long b = Long.parseLong(st.nextToken());

                long dif = b - series[a];
                series[a] = b;
                update(1, N, 1, a, dif);
            }

            if (command == 2) { // 계산
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                long num = sum(1, N, 1, a, b);
                sb.append(num + "\n");
            }

        }

        System.out.println(sb);
    }

    private static long init(int start, int end, int node) {

        if (start == end) {
            return tree[node] = series[start];
        }

        int mid = (start + end) / 2;

        return tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
    }

    private static long sum(int start, int end, int node, int left, int right) {

        if (left > end || right < start) return 0;

        if (left <= start && right >= end) return tree[node];

        int mid = (start + end) / 2;
        return sum(start, mid, node * 2, left, right)
                + sum(mid + 1, end, node * 2 + 1, left, right);
    }

    private static void update(int start, int end, int node, int idx, long value) {

        if (idx < start || idx > end) return;

        tree[node] += value;

        if (start == end) return;

        int mid = (start + end) / 2;
        update(start, mid, node * 2, idx, value);
        update(mid + 1, end, node * 2 + 1, idx, value);
    }

}
