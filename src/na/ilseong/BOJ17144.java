package na.ilseong;

import java.util.*;
import java.io.*;

public class BOJ17144 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static int R, C, T;
    static int[][] matrix;

    static int purifier;
    static Queue<Dirt> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        matrix = new int[R][C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < C; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < R; i++) { // 공기 청정기 위치 입력
            if (matrix[i][0] == -1) {
                purifier = i;
                break;
            }
        }

        while (T-- > 0) {

            beforeSpread(); // 큐에 확산될 먼지 위치 좌표 추가, 기존 미세 먼지 값 업데이트
            spread(); // 큐에서 좌표를 뽑아서 먼지 확산 시키기 (matrix 업데이트)
            operate(); // 공기 청정기 작동

        }

        int answer = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (matrix[i][j] > 0) {
                    answer += matrix[i][j];
                }
            }
        }

        System.out.println(answer);
    }

    private static void beforeSpread() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {

                if (matrix[i][j] >= 1) {

                    int value = matrix[i][j] / 5;
                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];

                        if (nx >= R || ny >= C || nx < 0 || ny < 0) continue;

                        if (matrix[nx][ny] == -1) continue;

                        queue.add(new Dirt(nx, ny, value));
                        matrix[i][j] -= value;
                    }

                }

            }
        }
    }

    private static void spread() {
        while (!queue.isEmpty()) {
            Dirt dirt = queue.poll();
            matrix[dirt.x][dirt.y] += dirt.value;
        }
    }

    private static void operate() {

        // 1
        for (int i = purifier - 1; i > 0; i--) {
            matrix[i][0] = matrix[i - 1][0];
        }
        for (int i = purifier + 2; i < R - 1; i++) {
            matrix[i][0] = matrix[i + 1][0];
        }

        // 2
        for (int i = 0; i < C - 1; i++) {
            matrix[0][i] = matrix[0][i + 1];
            matrix[R - 1][i] = matrix[R - 1][i + 1];
        }

        // 3
        for (int i = 0; i < purifier; i++) {
            matrix[i][C - 1] = matrix[i + 1][C - 1];
        }
        for (int i = R - 1; i > purifier + 1; i--) {
            matrix[i][C - 1] = matrix[i - 1][C - 1];
        }

        // 4
        for (int i = C - 1; i > 1; i--) {
            matrix[purifier][i] = matrix[purifier][i - 1];
            matrix[purifier + 1][i] = matrix[purifier + 1][i - 1];
        }
        matrix[purifier][1] = 0;
        matrix[purifier + 1][1] = 0;

    }

    private static class Dirt {
        int x, y, value;

        public Dirt(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }
    }

}