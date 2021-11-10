package na.ilseong.BOJ;

import java.util.*;
import java.io.*;

public class BOJ12100 {

    static int N;
    static int[][] matrix;

    static int ANSWER = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        matrix = new int[N][N];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int value = Integer.parseInt(st.nextToken());
                matrix[i][j] = value;
                ANSWER = Math.max(ANSWER, value);
            }
        }

        calculate(0);

        System.out.println(ANSWER);
    }

    private static void calculate(int count) {

        if (count == 5) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    ANSWER = Math.max(ANSWER, matrix[i][j]);
                }
            }
            return;
        }

        // 원본 생성
        int[][] original = new int[N][N];
        for (int i = 0; i < N; i++) {
            original[i] = matrix[i].clone();
        }

        for (int order = 0; order < 4; order++) {

            move(order);
            calculate(count + 1);

            // 원본 복원
            for (int i = 0; i < N; i++) {
                matrix[i] = original[i].clone();
            }

        }
        
    }

    private static void move(int order) {

        int idx;
        int preValue;

        if (order == 0) { // Up

            for (int y = 0; y < N; y++) {

                idx = 0;
                preValue = 0;

                for (int x = 0; x < N; x++) {

                    if (matrix[x][y] == 0) continue;

                    if (matrix[x][y] == preValue) {
                        matrix[idx - 1][y] = preValue * 2;
                        preValue = 0;
                        matrix[x][y] = 0;
                    } else {
                        matrix[idx][y] = matrix[x][y];
                        preValue = matrix[x][y];
                        if (idx != x) {
                            matrix[x][y] = 0;
                        }
                        idx++;
                    }

                }

            }

            return;
        }

        if (order == 1) { // Right

            for (int x = 0; x < N; x++) {

                idx = N - 1;
                preValue = 0;

                for (int y = N - 1; y >= 0; y--) {

                    if (matrix[x][y] == 0) continue;

                    if (matrix[x][y] == preValue) {
                        matrix[x][idx + 1] = preValue * 2;
                        matrix[x][y] = 0;
                        preValue = 0;
                    } else {
                        matrix[x][idx] = matrix[x][y];
                        preValue = matrix[x][y];
                        if (idx != y) {
                            matrix[x][y] = 0;
                        }
                        idx--;
                    }

                }

            }

            return;
        }

        if (order == 2) { // Down

            for (int y = 0; y < N; y++) {

                idx = N - 1;
                preValue = 0;

                for (int x = N - 1; x >= 0; x--) {

                    if (matrix[x][y] == 0) continue;

                    if (matrix[x][y] == preValue) {
                        matrix[idx + 1][y] = preValue * 2;
                        matrix[x][y] = 0;
                        preValue = 0;
                    } else {
                        matrix[idx][y] = matrix[x][y];
                        preValue = matrix[x][y];
                        if (idx != x) {
                            matrix[x][y] = 0;
                        }
                        idx--;
                    }

                }

            }

            return;
        }

        if (order == 3) { // Left

            for (int x = 0; x < N; x++) {

                idx = 0;
                preValue = 0;

                for (int y = 0; y < N; y++) {

                    if (matrix[x][y] == 0) continue;

                    if (matrix[x][y] == preValue) {
                        matrix[x][idx - 1] = preValue * 2;
                        matrix[x][y] = 0;
                        preValue = 0;
                    } else {
                        matrix[x][idx] = matrix[x][y];
                        preValue = matrix[x][y];
                        if (idx != y) {
                            matrix[x][y] = 0;
                        }
                        idx++;
                    }

                }

            }

            return;
        }

    }

}
