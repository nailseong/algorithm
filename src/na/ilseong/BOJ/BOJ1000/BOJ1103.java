package na.ilseong.BOJ.BOJ1000;

import java.util.*;
import java.io.*;

public class BOJ1103 {

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    static int N, M;
    static int[][] matrix, dp;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        matrix = new int[N][M];
        dp = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                char c = input.charAt(j);

                if (c == 'H') matrix[i][j] = -1;
                else matrix[i][j] = c - '0';
            }
        }

        visited[0][0] = true;
        System.out.println(dfs(0, 0));
    }

    private static int dfs(int x, int y) {

        if (dp[x][y] != 0) return dp[x][y];

        int tmp = 0;
        for (int i = 0; i < 4; i++) {

            int nx = x + (dx[i] * matrix[x][y]);
            int ny = y + (dy[i] * matrix[x][y]);


            if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                tmp = Math.max(tmp, 1);
                continue;
            }

            if (visited[nx][ny]) {
                System.out.println(-1);
                System.exit(0);
            }

            if (matrix[nx][ny] == -1) {
                tmp = Math.max(tmp, 1);
                continue;
            }

            visited[nx][ny] = true;
            tmp = Math.max(tmp, dfs(nx, ny) + 1);
            visited[nx][ny] = false;

        }

        return dp[x][y] = tmp;
    }

    private static void printMatrix() {
        System.out.println("========Print Matrix========");
        for (int[] ints : dp) {
            for (int i : ints) {
                if (i == -1) System.out.print(i + " ");
                else System.out.print(" " + i + " ");
            }
            System.out.println();
        }
    }

}
