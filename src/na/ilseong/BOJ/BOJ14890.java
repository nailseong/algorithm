package na.ilseong.BOJ;

import java.util.*;
import java.io.*;

public class BOJ14890 {

    static int N, L;
    static int[][] matrix;

    static int ANSWER = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        matrix = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            if (solve_column(i)) ANSWER++; // 세로
            if (solve_row(i)) ANSWER++; // 가로
        }

        System.out.println(ANSWER);
    }

    private static boolean solve_column(int y) {

        boolean[] visited = new boolean[N];

        for (int i = 0; i < N - 1; i++) {

            // 높이 같음
            if (matrix[i][y] == matrix[i + 1][y]) continue;

            // 높이가 2 이상
            if (Math.abs(matrix[i][y] - matrix[i + 1][y]) > 1) {
                return false;
            }

            if (matrix[i][y] + 1 == matrix[i + 1][y]) { // 올라가는 경사로
                for (int j = i; j > i - L; j--) {

                    if (j < 0) return false;
                    if (visited[j]) return false;
                    if (matrix[j][y] != matrix[i][y]) return false;

                    visited[j] = true;
                }

            }

            if (matrix[i][y] - 1 == matrix[i + 1][y]) { // 내려가는 경사로
                for (int j = i + 1; j <= i + L; j++) {

                    if (j >= N) return false;
                    if (visited[j]) return false;
                    if (matrix[j][y] != matrix[i + 1][y]) return false;

                    visited[j] = true;
                }
            }

        }

        return true;
    }

    private static boolean solve_row(int x) {

        boolean[] visited = new boolean[N];

        for (int i = 0; i < N - 1; i++) {

            // 높이 같음
            if (matrix[x][i] == matrix[x][i + 1]) continue;

            // 높이가 2 이상
            if (Math.abs(matrix[x][i] - matrix[x][i + 1]) > 1) {
                return false;
            }

            if (matrix[x][i] + 1 == matrix[x][i + 1]) { // 올라가는 경사로
                for (int j = i; j > i - L; j--) {

                    if (j < 0) return false;
                    if (visited[j]) return false;
                    if (matrix[x][j] != matrix[x][i]) return false;

                    visited[j] = true;
                }

            }

            if (matrix[x][i] - 1 == matrix[x][i + 1]) { // 내려가는 경사로
                for (int j = i + 1; j <= i + L; j++) {

                    if (j >= N) return false;
                    if (visited[j]) return false;
                    if (matrix[x][j] != matrix[x][i + 1]) return false;

                    visited[j] = true;
                }
            }

        }

        return true;
    }

}
