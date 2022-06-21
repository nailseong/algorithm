package na.ilseong.BOJ.BOJ9000;

import java.util.*;
import java.io.*;

public class BOJ9663 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N;
    static int[] matrix;

    static int ANSWER = 0;

    public static void main(String[] args) throws IOException {

        N = Integer.parseInt(br.readLine());
        matrix = new int[N];

        calculate(0);

        System.out.println(ANSWER);
    }

    static private void calculate(int idx) {

        if (idx == N) {
            ANSWER += 1;
            return;
        }

        for (int i = 0; i < N; i++) {
            matrix[idx] = i;
            if (check(idx)) {
                calculate(idx + 1);
            }
        }
    }

    static private boolean check(int idx) {
        for (int i = 0; i < idx; i++) {

            if (matrix[i] == matrix[idx]) return false;

            int x = Math.abs(i - idx);
            int y = Math.abs(matrix[i] - matrix[idx]);

            if (x == y) return false;

        }

        return true;
    }

}
