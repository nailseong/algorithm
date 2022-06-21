package na.ilseong.BOJ.BOJ15000;

import java.util.*;
import java.io.*;

public class BOJ15684 {

    static int N, M, H;
    static int[][] matrix;

    static boolean isFinish;

    static int COUNT = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        matrix = new int[H][N];

        if (M == 0) {
            System.out.println(0);
            return;
        }

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            matrix[a - 1][b - 1] = 1;
            matrix[a - 1][b] = -1;
        }

        for (int i = 0; i <= 3; i++) {

            isFinish = false;
            dfs(0, i);
            if (isFinish) {
                COUNT = i;
                break;
            }

        }

        System.out.println(COUNT);
    }

    private static void dfs(int count, int target) {

        if (isFinish) return;

        if (count == target) {
            if (play()) isFinish = true;
            return;
        }

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N - 1; j++) {
                if (matrix[i][j] == 0 && matrix[i][j + 1] == 0) {

                    // 사다리 설치
                    matrix[i][j] = 1;
                    matrix[i][j + 1] = -1;

                    dfs(count + 1, target);

                    // 사다리 되돌리기
                    matrix[i][j] = 0;
                    matrix[i][j + 1] = 0;

                }
            }
        }

    }

    private static boolean play() {

        boolean isSame = true;

        for (int y = 0; y < N; y++) {
            int idx = y;

            for (int x = 0; x < H; x++) {
                idx += matrix[x][idx];
            }
            
            if (idx != y) {
                isSame = false;
                break;
            }
        }

        return isSame;
    }
}
