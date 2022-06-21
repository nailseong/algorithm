package na.ilseong.BOJ.BOJ14000;

import java.util.*;
import java.io.*;

public class BOJ14503 {

    // 북, 동, 남, 서 ( 시계 방향 )
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    static int N, M;
    static int[][] matrix;
    static int x, y, dir;

    static int COUNT;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        dir = Integer.parseInt(st.nextToken());

        matrix = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve();

        System.out.println(COUNT);
    }

    private static void solve() {

        boolean isFinish;
        while (true) {

            isFinish = true;

            if (matrix[x][y] == 0) { // 현재 위치 청소
                COUNT++;
                matrix[x][y] = -1;
                isFinish = false;
            }

            int currentDir = dir;
            boolean isUpdatable = false;
            for (int i = 0; i < 4; i++) {
                currentDir = (currentDir + 3) % 4;
                int nx = x + dx[currentDir];
                int ny = y + dy[currentDir];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;

                if (matrix[nx][ny] != 0) continue;

                isUpdatable = true;
                isFinish = false;
                dir = currentDir;
                x = nx;
                y = ny;
                break;

            }

            if (!isUpdatable) { // 후진
                int nx = x - dx[dir];
                int ny = y - dy[dir];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M) isFinish = true;

                if (matrix[nx][ny] != 1) {
                    x = nx;
                    y = ny;
                    isFinish = false;
                }

            }

            if (isFinish) break;

        }

    }

    private static void print() {
        for (int[] ints : matrix) {
            for (int i : ints) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
