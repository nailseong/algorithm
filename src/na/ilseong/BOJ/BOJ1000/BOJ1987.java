package na.ilseong.BOJ.BOJ1000;

import java.util.*;
import java.io.*;

public class BOJ1987 {

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static int R;
    static int C;

    static int[][] matrix;
    static boolean[] visited = new boolean[26];

    static int ANSWER = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        matrix = new int[R][C];

        for (int i = 0; i < R; i++) {
            String line = br.readLine();

            for (int j = 0; j < C; j++) {
                matrix[i][j] = line.charAt(j) - 'A';
            }
        }

        dfs(0, 0, 0);

        System.out.println(ANSWER);
    }

    private static void dfs(int x, int y, int count) {
        int currentValue = matrix[x][y];

        if (visited[currentValue]) {
            ANSWER = Math.max(count, ANSWER);
        } else {
            visited[currentValue] = true;
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && nx < R && ny >= 0 && ny < C) {
                    dfs(nx, ny, count + 1);
                }
            }
            // 되돌아 오면서 방문처리 취소
            visited[currentValue] = false;
        }

    }

}
