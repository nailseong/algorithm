package na.ilseong.BOJ;

import java.util.*;
import java.io.*;

public class BOJ2206 {

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static int N;
    static int M;

    static char[][] matrix;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        matrix = new char[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                matrix[i][j] = line.charAt(j);
            }
        }

        bfs();
    }

    private static void bfs() {

        int result = -1;

        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0, 0, 1, 0));

        int[][] visited = new int[N][M];
        for (int[] ints : visited) {
            Arrays.fill(ints, Integer.MAX_VALUE);
        }
        visited[0][0] = 0;

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            if (node.x == N - 1 && node.y == M - 1) {
                result = node.distance;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                // matrix 좌표 벗어남
                if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                    continue;
                }

                // node.destroy == 0 이고, visited[nx][ny] == 1 일 때도 다시 방문할 수 있다.
                // 벽을 부수지 않았으니까 (node.destroy == 0 이니까)
                if (visited[nx][ny] <= node.destroy) {
                    continue;
                }

                if (matrix[nx][ny] == '0') {
                    visited[nx][ny] = node.destroy;
                    queue.add(new Node(nx, ny, node.distance + 1, node.destroy));
                } else {
                    if (node.destroy == 0) {
                        visited[nx][ny] = node.destroy + 1;
                        queue.add(new Node(nx, ny, node.distance + 1, node.destroy + 1));
                    }
                }
            }

        }

        System.out.println(result);
    }

    private static class Node {

        int x;
        int y;
        int distance;
        int destroy;

        public Node(int x, int y, int distance, int destroy) {
            this.x = x;
            this.y = y;
            this.distance = distance;
            this.destroy = destroy;
        }
    }

}
