package na.ilseong;

import java.util.*;
import java.io.*;

public class BOJ11444 {

    static long N;

    static long[][] origin = {
            {1, 1},
            {1, 0}
    };
    static long[][] A = {
            {1, 0},
            {0, 1}
    };

    static int NUM = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Long.parseLong(br.readLine());

        if (N == 0 || N == 1) {
            System.out.println(N);
            return;
        }

        N--;
        while (N > 0) {
            if (N % 2 == 1) {
                A = calculate(A, origin);
            }
            origin = calculate(origin, origin);

            N /= 2;
        }

        System.out.println(A[0][0]);
    }

    private static long[][] calculate(long[][] o1, long[][] o2) {

        long[][] tmp = new long[2][2];

        tmp[0][0] = ((o1[0][0] * o2[0][0]) + (o1[0][1] * o2[1][0])) % NUM;
        tmp[0][1] = ((o1[0][0] * o2[0][1]) + (o1[0][1] * o2[1][1])) % NUM;
        tmp[1][0] = ((o1[1][0] * o2[0][0]) + (o1[1][1] * o2[1][0])) % NUM;
        tmp[1][1] = ((o1[1][0] * o2[0][1]) + (o1[1][1] * o2[1][1])) % NUM;

        return tmp;
    }

}
