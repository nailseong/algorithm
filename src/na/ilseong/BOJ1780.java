package na.ilseong;

import java.util.*;
import java.io.*;

public class BOJ1780 {

    static int N;
    static int[][] matrix;

    static int[] answer = new int[3];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        matrix = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        String result = solve(N, 0, 0);
        for (int i = 0; i < result.length(); i++) {

            if (result.charAt(i) == '0') answer[0]++;
            if (result.charAt(i) == '1') answer[1]++;
            if (result.charAt(i) == '2') answer[2]++;

        }

        for (int a : answer) {
            System.out.println(a);
        }
    }

    private static String solve(int n, int r, int c) {

        if (n == 1) {
            return String.valueOf(matrix[r][c] + 1);
        }

        StringBuilder sb = new StringBuilder();

        sb.append(solve(n / 3, r, c));
        sb.append(solve(n / 3, r, c + n / 3));
        sb.append(solve(n / 3, r, c + n / 3 * 2));

        sb.append(solve(n / 3, r + n / 3, c));
        sb.append(solve(n / 3, r + n / 3, c + n / 3));
        sb.append(solve(n / 3, r + n / 3, c + n / 3 * 2));

        sb.append(solve(n / 3, r + n / 3 * 2, c));
        sb.append(solve(n / 3, r + n / 3 * 2, c + n / 3));
        sb.append(solve(n / 3, r + n / 3 * 2, c + n / 3 * 2));

        boolean isSame = true;
        for (int i = 0; i < 8; i++) {
            if (sb.charAt(i) != sb.charAt(i + 1)) {
                isSame = false;
                break;
            }
        }

        if (isSame) {
            return String.valueOf(sb.charAt(0));
        } else {
            return sb.toString();
        }

    }

}
