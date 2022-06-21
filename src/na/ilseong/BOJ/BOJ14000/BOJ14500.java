package na.ilseong.BOJ.BOJ14000;

import java.util.*;
import java.io.*;

public class BOJ14500 {

    // 하, 상, 우, 좌
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    // 하, 상 -> x 동일
    // 우, 좌 -> y 동일
    static int[] dx_1 = {1, -1, -1, 1};
    static int[] dy_1 = {-1, 1, 1, -1};

    static int[] dx_2 = {1, -1, 1, -1};
    static int[] dy_2 = {1, -1, 1, -1};

    static int N, M;
    static int[][] matrix;
    static boolean[][] visited;

    static int ANSWER = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        matrix = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dfs(i, j, 0, 0);
                extraCheck(i, j); // ㅗ 모양 체크
            }
        }

        System.out.println(ANSWER);
    }

    private static void dfs(int x, int y, int value, int count) {

        if (count == 4) {
            ANSWER = Math.max(ANSWER, value);
            return;
        }

        for (int i = 0; i < 4; i++) {

            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;

            if (visited[nx][ny]) continue;

            visited[nx][ny] = true;
            dfs(nx, ny, value + matrix[nx][ny], count + 1);
            visited[nx][ny] = false;
        }

    }

    private static void extraCheck(int x, int y) {

        for (int i = 0; i < 4; i++) {

            int nx = x + dx[i];
            int ny = y + dy[i];

            int nx_1 = x + dx_1[i];
            int ny_1 = y + dy_1[i];

            int nx_2 = x + dx_2[i];
            int ny_2 = y + dy_2[i];

            if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;

            if (nx_1 < 0 || nx_1 >= N || ny_1 < 0 || ny_1 >= M) continue;

            if (nx_2 < 0 || nx_2 >= N || ny_2 < 0 || ny_2 >= M) continue;

            int value = matrix[x][y] + matrix[nx][ny] + matrix[nx_1][ny_1] + matrix[nx_2][ny_2];
            ANSWER = Math.max(ANSWER, value);

        }

    }

}
