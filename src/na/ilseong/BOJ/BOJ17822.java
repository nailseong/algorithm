package na.ilseong.BOJ;

import java.util.*;
import java.io.*;

public class BOJ17822 {

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static int N, M, T;
    static int[][] matrix;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        matrix = new int[N + 1][M];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            rotate(x, d, k);
            if (!delete()) update();

        }

        System.out.println(sum());
    }

    private static int sum() {
        int count = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                count += matrix[i][j];
            }
        }

        return count;
    }

    private static int count() {
        int count = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                if (matrix[i][j] != 0) count++;
            }
        }

        return count;
    }

    private static void update() {
        int sum = sum();
        int count = count();

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                if (matrix[i][j] != 0) {

                    if (sum > matrix[i][j] * count) matrix[i][j]++;

                    else if (sum < matrix[i][j] * count) matrix[i][j]--;

                }
            }
        }
    }

    private static boolean delete() {

        boolean isDeleted = false;
        boolean[][] visited = new boolean[N + 1][M]; // 삭제 예정

        for (int x = 1; x <= N; x++) {
            for (int y = 0; y < M; y++) {

                if (matrix[x][y] == 0) continue;

                boolean flag = false;
                for (int i = 0; i < 4; i++) {

                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    if (nx < 1 || nx > N) continue;

                    if (ny == -1) ny = M - 1;

                    if (ny == M) ny = 0;

                    if (matrix[x][y] != matrix[nx][ny]) continue;

                    if (visited[nx][ny]) continue;

                    visited[nx][ny] = true;
                    flag = true;
                }

                if (flag) {
                    visited[x][y] = true;
                }

            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j]) {
                    matrix[i][j] = 0;
                    isDeleted = true;
                }
            }
        }

        return isDeleted;
    }

    private static void rotate(int x, int d, int k) {

        int tmp = 1;
        while (true) {

            int[] circle = matrix[x * tmp];

            int[] newCircle = new int[M];
            if (d == 0) { // 시계 방향
                System.arraycopy(circle, 0, newCircle, k, M - k);
                System.arraycopy(circle, M - k, newCircle, 0, k);
            }

            if (d == 1) { // 반시계 방향
                System.arraycopy(circle, 0, newCircle, M - k, k);
                System.arraycopy(circle, k, newCircle, 0, M - k);
            }

            matrix[x * tmp] = newCircle;

            if (N >= x * (tmp + 1)) tmp++;
            else break;

        }

    }
}
