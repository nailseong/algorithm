package na.ilseong.BOJ.BOJ2000;

import java.util.*;
import java.io.*;

public class BOJ2638 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static int N, M;
    static int[][] matrix;

    static Queue<Node> queue = new LinkedList<>();
    static boolean[][] visited;
    static boolean isUpdatable = false;

    static int ANSWER = 0;

    private static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // -1 -> 외부 공기
        // 0 -> 내부 공기
        // 1 -> 치즈
        matrix = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bfs_out(); // 외부 공기 탐색 (BFS) & matrix 업데이트

        bfs_cheese(); // 큐에 치즈 좌표 입력

        while (isUpdatable) {
            // 큐에 들어있는 치즈 좌표를 토대로 matrix 업데이트, ANSWER++
            ANSWER++;

            while (!queue.isEmpty()) { // 치즈 녹이기
                Node cheese = queue.poll();
                matrix[cheese.x][cheese.y] = -1;
            }

            for (int i = 0; i < N; i++) { // matrix 롤백
                for (int j = 0; j < M; j++) {
                    if (matrix[i][j] == -1) matrix[i][j] = 0;
                }
            }

            bfs_out(); // 외부 공기 업데이트

            bfs_cheese(); // 치즈 탐색
        }

        System.out.println(ANSWER);
    }

    private static void bfs_out() {

        queue.add(new Node(0, 0));
        matrix[0][0] = -1;

        while (!queue.isEmpty()) {

            Node node = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                if (nx >= N || nx < 0 || ny >= M || ny < 0) continue;

                // -1 이면 이미 방문한 공기 (외부)
                // 0 이면 방문하지 않은 공기 (무조건 외부 공기)
                // 외부 공기에 인접한 공기가 내부 공기일 수 없음
                // 즉, matrix[nx][ny]가 0 일 때 방문 여부를 확인할 필요 X
                if (matrix[nx][ny] != 0) continue;

                queue.add(new Node(nx, ny));
                matrix[nx][ny] = -1;
            }

        }

    }

    private static void bfs_cheese() {

        visited = new boolean[N][M];
        queue.clear();
        isUpdatable = false;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {

                if (matrix[i][j] == 1 && !visited[i][j]) { // 방문하지 않은 치즈

                    visited[i][j] = true;
                    int count = 0;

                    for (int k = 0; k < 4; k++) { // 상, 하, 좌, 우 확인

                        int nx = i + dx[k];
                        int ny = j + dy[k];

                        if (matrix[nx][ny] != -1) continue; // 외부 공기가 아니면 패스

                        count++;

                    }

                    if (count >= 2) { // 외부 공기 2개 이상 둘러 쌓여 있을 때
                        queue.add(new Node(i, j));
                        isUpdatable = true;
                    }

                }

            }
        }

    }

}
