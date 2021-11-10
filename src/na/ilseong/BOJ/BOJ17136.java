package na.ilseong.BOJ;

import java.util.*;
import java.io.*;

public class BOJ17136 {

    static int[][] matrix = new int[10][10];

    static int COUNT = 26;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 0; i < 10; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] paper = {0, 5, 5, 5, 5, 5};
        solve(0, 0, paper);

        if (COUNT == 26) System.out.println(-1);
        else System.out.println(COUNT);
    }

    private static void solve(int x, int y, int[] paper) {

        if (y == 10) { // 다음 행으로 넘어가기
            solve(x + 1, 0, paper);
            return;
        }

        if (x == 10) { // 최솟값 계산
            int tmp = 25;
            for (int i : paper) {
                tmp -= i;
            }
            COUNT = Math.min(COUNT, tmp);
            return;
        }

        if (matrix[x][y] == 0) solve(x, y + 1, paper); // 색종이 붙일 수 없음

        for (int size = 1; size <= 5; size++) { // 크기 1 ~ 5 색종이 붙이기

            if (paper[size] > 0) {
                if (check(x, y, size)) {

                    paper[size]--;
                    for (int i = x; i < x + size; i++) {
                        for (int j = y; j < y + size; j++) {
                            matrix[i][j] = 0;
                        }
                    }

                    solve(x, y + size, paper);

                    paper[size]++;
                    for (int i = x; i < x + size; i++) {
                        for (int j = y; j < y + size; j++) {
                            matrix[i][j] = 1;
                        }
                    }

                }
            }

        }

    }

    private static boolean check(int x, int y, int length) {

        if (x + length > 10 || y + length > 10) return false;

        for (int i = x; i < x + length; i++) {
            for (int j = y; j < y + length; j++) {
                if (matrix[i][j] != 1) {
                    return false;
                }
            }
        }

        return true;
    }

}
