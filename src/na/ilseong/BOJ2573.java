package na.ilseong;

import java.util.*;
import java.io.*;

public class BOJ2573 {

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static int N;
    static int M;

    static int[][] matrix;

    static int COUNT = 0;
    static int ANSWER = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        matrix = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        // 정답 계산 -> 빙산이 2개 이상 or 0개 : 종료
        while ((COUNT = countIce()) < 2) {
            if (COUNT == 0) {
                ANSWER = 0;
                break;
            }

            // 빙산 녹이기 -> ANSWER++
            meltIce();
            ANSWER++;
        }

        System.out.println(ANSWER);
    }

    // 빙산 녹이기
    private static void meltIce() {
        
        boolean[][] visited = new boolean[N][M];
        Queue<Ice> queue = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (matrix[i][j] != 0) {
                    queue.offer(new Ice(i, j));
                    visited[i][j] = true;
                }
            }
        }

        while (!queue.isEmpty()) {

            Ice ice = queue.poll();
            int count = 0;

            for (int i = 0; i < 4; i++) {
                int nx = ice.x + dx[i];
                int ny = ice.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                    continue;
                }

                if (matrix[nx][ny] == 0 && !visited[nx][ny]) {
                    count++;
                }
            }

            matrix[ice.x][ice.y] = Math.max(matrix[ice.x][ice.y] - count, 0);
        }
        
    }

    // 빙산 개수 계산
    private static int countIce() {

        boolean[][] visited = new boolean[N][M];
        int count = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (matrix[i][j] != 0 && !visited[i][j]) {
                    dfs(i, j, visited);
                    count++;
                }
            }
        }

        return count;
    }

    // 빙산 개수 계산
    private static void dfs(int x, int y, boolean[][] visited) {

        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                continue;
            }

            if (matrix[nx][ny] != 0 && !visited[nx][ny]) {
                dfs(nx, ny, visited);
            }

        }

    }

    private static class Ice {

        int x;
        int y;

        public Ice(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }

}
