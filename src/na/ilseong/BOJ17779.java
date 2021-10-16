package na.ilseong;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17779 {

    static int N;
    static int[][] original;
    static int[][] matrix;
    static boolean[][] visited;

    static int ANSWER = 40_001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        original = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                original[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int x = 1; x <= N; x++) {
            for (int y = 1; y <= N; y++) {

                for (int d2 = 1; d2 <= N - y; d2++) {
                    for (int d1 = 1; d1 <= N - x; d1++) {
                        if (0 >= d1 + d2) continue;

                        if (N - x < d1 + d2) continue;

                        if (1 > y - d1) continue;

//                        System.out.println("x = " + x + " / y = " + y + " / d1 = " + d1 + " / d2 = " + d2);

                        // matrix 복사
                        copy();

                        // 경계선 생성
                        split(x, y, d1, d2);
                        calculate(x, y, d1, d2);

                    }
                }

            }
        }

        System.out.println(ANSWER);
    }

    private static void calculate(int x, int y, int d1, int d2) {

        int MIN = 40_001;
        int MAX = 0;
        visited = new boolean[N + 1][N + 1];

        int first = first(x, y, d1);
        MIN = Math.min(MIN, first);
        MAX = Math.max(MAX, first(x, y, d1));

        int second = second(x, y, d2);
        MIN = Math.min(MIN, second);
        MAX = Math.max(MAX, second);

        int third = third(x, y, d1, d2);
        MIN = Math.min(MIN, third);
        MAX = Math.max(MAX, third);

        int fourth = fourth(x, y, d1, d2);
        MIN = Math.min(MIN, fourth);
        MAX = Math.max(MAX, fourth);

        int fifth = fifth();
        MIN = Math.min(MIN, fifth);
        MAX = Math.max(MAX, fifth);

        ANSWER = Math.min(ANSWER, MAX - MIN);
    }

    private static int first(int x, int y, int d1) {
        int count = 0;

        for (int i = 1; i < x + d1; i++) {
            for (int j = 1; j <= y; j++) {

                if (matrix[i][j] < 0) break;

                count += matrix[i][j];
                visited[i][j] = true;

            }
        }

        return count;
    }

    private static int second(int x, int y, int d2) {
        int count = 0;

        for (int i = 1; i <= x + d2; i++) {
            for (int j = N; j > y; j--) {

                if (matrix[i][j] < 0) break;

                count += matrix[i][j];
                visited[i][j] = true;

            }
        }

        return count;
    }

    private static int third(int x, int y, int d1, int d2) {
        int count = 0;

        for (int i = x + d1; i <= N; i++) {
            for (int j = 1; j < y - d1 + d2; j++) {

                if (matrix[i][j] < 0) break;

                count += matrix[i][j];
                visited[i][j] = true;

            }
        }

        return count;
    }

    private static int fourth(int x, int y, int d1, int d2) {
        int count = 0;

        for (int i = x + d2 + 1; i <= N; i++) {
            for (int j = N; j >= y - d1 + d2; j--) {

                if (matrix[i][j] < 0) break;

                count += matrix[i][j];
                visited[i][j] = true;

            }
        }

        return count;
    }

    private static int fifth() {
        int count = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (!visited[i][j]) {
                    count += Math.abs(matrix[i][j]);
                }
            }
        }

        return count;
    }

    private static void split(int x, int y, int d1, int d2) {

        // 1
        for (int i = 0; i <= d1; i++) {
            matrix[x + i][y - i] = -Math.abs(matrix[x + i][y - i]);
        }

        // 2
        for (int i = 0; i <= d2; i++) {
            matrix[x + i][y + i] = -Math.abs(matrix[x + i][y + i]);
        }

        // 3
        for (int i = 0; i <= d2; i++) {
            matrix[x + d1 + i][y - d1 + i] = -Math.abs(matrix[x + d1 + i][y - d1 + i]);
        }

        for (int i = 0; i <= d1; i++) {
            matrix[x + d2 + i][y + d2 - i] = -Math.abs(matrix[x + d2 + i][y + d2 - i]);
        }

    }

    private static void copy() {
        matrix = new int[N + 1][N + 1];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= N; j++) {
                matrix[i][j] = original[i][j];
            }
        }
    }

}
