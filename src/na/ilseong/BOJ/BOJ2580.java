package na.ilseong.BOJ;

import java.util.*;
import java.io.*;

public class BOJ2580 {

    static int[][] matrix = new int[9][9];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve(0, 0);

    }

    private static void solve(int x, int y) {

        if (y == 9) {
            solve(x + 1, 0);
            return;
        }

        if (x == 9) {
            print();
            System.exit(0);
        }

        if (matrix[x][y] == 0) {

            for (int value = 1; value <= 9; value++) {
                if (isValid(x, y, value)) {
                    matrix[x][y] = value;
                    solve(x, y + 1);
                }
            }

            matrix[x][y] = 0;
            return;
        }

        solve(x, y + 1);

    }

    private static boolean isValid(int x, int y, int value) {

        for (int i = 0; i < 9; i++) {

            // 가로
            if (matrix[x][i] == value) return false;

            // 세로
            if (matrix[i][y] == value) return false;

        }

        // 사각형
        int startX = (x / 3) * 3;
        int startY = (y / 3) * 3;
        for (int i = startX; i < startX + 3; i++) {
            for (int j = startY; j < startY + 3; j++) {
                if (matrix[i][j] == value) return false;
            }
        }

        return true;
    }

    private static void print() {
        for (int[] ints : matrix) {
            for (int i : ints) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

}
