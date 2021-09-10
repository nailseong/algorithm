package na.ilseong;

import java.util.*;
import java.io.*;

public class BOJ1261 {

    static int N;
    static int M;
    static int[][] graph;

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        graph = new int[N + 1][M + 1];

        for (int i = 1; i < N + 1; i++) {

            st = new StringTokenizer(br.readLine());
            String[] strings = st.nextToken().split("");

            for (int j = 1; j < M + 1; j++) {
                graph[i][j] = Integer.parseInt(strings[j - 1]);
            }

        }

        System.out.println(bfs());
    }

    private static int bfs() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(1, 1, 0));

        boolean[][] visited = new boolean[N + 1][M + 1];
        visited[1][1] = true;

        int nx;
        int ny;

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            if (current.x == N && current.y == M) {
                return current.count;
            }

            for (int i = 0; i < 4; i++) {

                nx = current.x + dx[i];
                ny = current.y + dy[i];

                if (nx < 1 || nx > N || ny < 1 || ny > M) {
                    continue;
                }

                if (!visited[nx][ny]) {

                    if (graph[nx][ny] == 0) {
                        pq.offer(new Node(nx, ny, current.count));
                    } else {
                        pq.offer(new Node(nx, ny, current.count + 1));
                    }

                    visited[nx][ny] = true;
                }

            }

        }

        return 0;
    }

    private static class Node implements Comparable<Node> {

        int x;
        int y;
        int count;

        public Node(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }

        @Override
        public int compareTo(Node n) {
            return this.count - n.count;
        }
    }

}
