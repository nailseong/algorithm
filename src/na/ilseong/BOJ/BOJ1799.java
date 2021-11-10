package na.ilseong.BOJ;

import java.util.*;
import java.io.*;

public class BOJ1799 {

    static int N;
    static int[][] matrix;
    static boolean[] d = new boolean[20];

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

        System.out.println(solve(1) + solve(2));

    }

    /*
    N == 5
    num = 대각선 번호 (1 ~ 2N-1 번)
        6  7  8  9
    5 ↘︎ ↘︎ ↘︎ ↘︎ ↘︎
    4 ↘︎ ↘︎ ↘︎ ↘︎ ↘︎
    3 ↘︎ ↘︎ ↘︎ ↘︎ ↘︎
    2 ↘︎ ↘︎ ↘︎ ↘︎ ↘︎
    1 ↘︎ ↘︎ ↘︎ ↘︎ ↘︎

    x + y + 1 번호
     1  2  3  4  5
    /  /  /  /  /
    /  /  /  /  / 6
    /  /  /  /  / 7
    /  /  /  /  / 8
    /  /  /  /  / 9
    */
    private static int solve(int num) {

        if (num > 2 * N - 1) return 0;

        int max = -1;
        int x = num < N ? N - num : 0;
        int y = num < N ? 0 : num - N;

        // "\" 방향으로 탐색
        // d[x + y + 1] => "/" 방향에 비숍이 존재하는지 여부
        while (x < N && y < N) {
            if (matrix[x][y] == 1 && !d[x + y + 1]) {
                d[x + y + 1] = true;
                max = Math.max(max, solve(num + 2) + 1);
                d[x + y + 1] = false;
            }
            x++;
            y++;
        }

        if (max < 0) max = solve(num + 2);

        return max;
    }

}
