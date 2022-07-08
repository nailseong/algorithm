package na.ilseong.BOJ.BOJ3000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ3085 {
    private static int n;
    private static char[][] matrix;
    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {1, 0, -1, 0};
    private static int answer = 0;

    public static void main(String[] args) throws Exception {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        matrix = new char[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String input = st.nextToken();
            for (int j = 0; j < n; j++) {
                matrix[i][j] = input.charAt(j);
            }
        }

        solve();

        bw.write(Integer.toString(answer));

        bw.flush();
        bw.close();
    }

    private static void solve() {
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    if (nx < 0 || nx >= n) {
                        continue;
                    }
                    if (ny < 0 || ny >= n) {
                        continue;
                    }
                    char a = matrix[x][y];
                    char b = matrix[nx][ny];

                    matrix[x][y] = b;
                    matrix[nx][ny] = a;

                    hello();

                    matrix[x][y] = a;
                    matrix[nx][ny] = b;
                }
            }
        }
    }

    private static void hello() {
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                calculate(x, y);
            }
        }
    }

    private static void calculate(int x, int y) {
        char c = matrix[x][y];
        for (int i = 0; i < 4; i++) {
            int count = 0;
            int nx = x;
            int ny = y;
            while (0 <= nx && nx < n && 0 <= ny && ny < n && matrix[nx][ny] == c) {
                count++;
                nx = nx + dx[i];
                ny = ny + dy[i];
            }
            if (count > answer) {
                answer = count;
            }
        }
    }
}
