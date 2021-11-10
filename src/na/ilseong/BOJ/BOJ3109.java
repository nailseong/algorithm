package na.ilseong.BOJ;

import java.util.*;
import java.io.*;

public class BOJ3109 {

    static int[] dx = {-1, 0, 1};

    static int R,C;
    static boolean[][] matrix;

    static int ANSWER = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        matrix = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            String input = br.readLine();
            for (int j = 0; j < C; j++) {
                char c = input.charAt(j);
                if (c == 'x') matrix[i][j] = true;
            }
        }

        for (int x = 0; x < R; x++) dfs(x, 0);

        System.out.println(ANSWER);
    }

    private static boolean dfs(int x, int y) {

        if (y == C - 1) {
            ANSWER++;
            return true;
        }

        for (int i = 0; i < 3; i++) {
            int nx = x + dx[i];

            if (nx < 0 || nx >= R) continue;

            if (matrix[nx][y + 1]) continue;

            matrix[nx][y + 1] = true; // 파이프 설치

            boolean tmp = dfs(nx, y + 1); // y == C - 1에 도달하면 true
            if (tmp) return true; // tmp가 false여도 파이프 해제할 필요가 없음

        }

        return false;
    }

}
