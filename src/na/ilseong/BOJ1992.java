package na.ilseong;

import java.util.*;
import java.io.*;

public class BOJ1992 {

    static int N;
    static char[][] matrix;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        matrix = new char[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            String line = br.readLine();
            for (int j = 1; j <= N; j++) {
                matrix[i][j] = line.charAt(j - 1);
            }
        }

        System.out.println(solve(N, 1, 1));
    }

    private static String solve(int n, int row, int column) {

        if (n == 1) {
            return String.valueOf(matrix[row][column]);
        }

        String a = solve(n / 2, row, column);
        String b = solve(n / 2, row, column + n / 2);
        String c = solve(n / 2, row + n / 2, column);
        String d = solve(n / 2, row + n / 2, column + n / 2);

        if (a.equals(b) && b.equals(c) && c.equals(d) && a.length() == 1) {
            return a;
        } else {
            return "(" + a + b + c + d + ")";
        }

    }

}
