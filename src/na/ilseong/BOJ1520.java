package na.ilseong;

import java.util.*;
import java.io.*;

public class BOJ1520 {

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static int R, C;
    static int[][] matrix;
    static boolean[][] visited;

    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        matrix = new int[R + 1][C + 1];
        dp = new int[R + 1][C + 1];
        visited = new boolean[R + 1][C + 1];
        for (int i = 1; i <= R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= C; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(dfs(1, 1));
    }

    private static int dfs(int r, int c) {

        if (r == R && c == C) {
            return 1;
        }

        if (visited[r][c]) {
            return dp[r][c];
        }

        visited[r][c] = true;

        for (int i = 0; i < 4; i++) {
            int nx = r + dx[i];
            int ny = c + dy[i];

            if (nx < 1 || nx > R || ny < 1 || ny > C) continue;

            if (matrix[nx][ny] >= matrix[r][c]) continue;

            dp[r][c] += dfs(nx, ny);

        }

        return dp[r][c];
    }

}
