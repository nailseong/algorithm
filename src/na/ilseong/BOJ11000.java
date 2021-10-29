package na.ilseong;

import java.util.*;
import java.io.*;

public class BOJ11000 {

    static int N;
    static Node[] series;
    static PriorityQueue<Integer> pQueue = new PriorityQueue<>();

    private static class Node {
        int start, end;

        public Node(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        series = new Node[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            series[i] = new Node(start, end);
        }

        Arrays.sort(series,
                (n1, n2) -> n1.start != n2.start
                            ? n1.start - n2.start
                            : n1.end - n2.end
        );

        pQueue.offer(series[0].end);
        for (int i = 1; i < N; i++) {
            if (pQueue.peek() <= series[i].start) pQueue.poll();
            pQueue.offer(series[i].end);
        }

        System.out.println(pQueue.size());
    }

}
