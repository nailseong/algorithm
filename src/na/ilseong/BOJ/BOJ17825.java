package na.ilseong.BOJ;

import java.util.*;
import java.io.*;

public class BOJ17825 {

    static int[] series = new int[10];
    static boolean[][] visited;
    static int[][] matrix = {
            {0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40},
            {0, 0, 0, 0, 0,  0, 13, 16, 19, 25, 30, 35, 40},
            {0, 0, 0, 0, 0,  0,  0,  0,  0,  0,  0, 22, 24, 25, 30, 35, 40},
            {0, 0, 0, 0, 0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 28, 27, 26, 25, 30, 35, 40}
    };
    static ArrayList<Node> list = new ArrayList<>();

    static int ANSWER = 0;

    private static class Node {
        int x, y;
        boolean inField;

        public Node(int x, int y, boolean inField) {
            this.x = x;
            this.y = y;
            this.inField = inField;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < 10; i++) {
            series[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < 4; i++) {
            list.add(new Node(0, 0, false));
        }

        visited = new boolean[4][];
        visited[0] = new boolean[21];
        visited[1] = new boolean[13];
        visited[2] = new boolean[17];
        visited[3] = new boolean[23];

        solve(0, 0);

        System.out.println(ANSWER);
    }

    private static void solve(int count, int sum) {

        if (count == 10) {
            ANSWER = Math.max(ANSWER, sum);
            return;
        }

        int value = series[count];
        for (Node node : list) {

            // "시작"에 위차한 말 이동
            if (node.x == 0 && node.y == 0) {

                if (visited[node.x][node.y + value]) continue;

                node.y += value;
                node.inField = true;
                visited[node.x][node.y] = true;

                solve(count + 1, sum + matrix[node.x][node.y]);

                visited[node.x][node.y] = false;
                node.y -= value;
                node.inField = false;

                continue;
            }

            if (!node.inField) continue;

            // 파란 화살표를 타고 이동
            if (node.x == 0 && (node.y == 5 || node.y == 10 || node.y == 15)) {

                // 이미 말이 존재함
                if (visited[matrix[node.x][node.y] / 10][node.y + value]) continue;

                // 25
                if (matrix[matrix[node.x][node.y] / 10][node.y + value] == 25) {
                    if (visited[1][9] || visited[2][13] || visited[3][19]) continue;
                }

                // 30
                if (matrix[matrix[node.x][node.y] / 10][node.y + value] == 30) {
                    if (visited[1][10] || visited[2][14] || visited[3][20]) continue;
                }

                // 35
                if (matrix[matrix[node.x][node.y] / 10][node.y + value] == 35) {
                    if (visited[1][11] || visited[2][15] || visited[3][21]) continue;
                }

                // 40
                if (matrix[matrix[node.x][node.y] / 10][node.y + value] == 40) {
                    if (visited[0][20] || visited[1][12] || visited[2][16] || visited[3][22]) continue;
                }

                visited[node.x][node.y] = false;
                node.x = matrix[node.x][node.y] / 10;
                node.y += value;
                visited[node.x][node.y] = true;

                solve(count + 1, sum + matrix[node.x][node.y]);

                visited[node.x][node.y] = false;
                node.x = 0;
                node.y -= value;
                visited[node.x][node.y] = true;

                continue;
            }

            // 빨간 화살표
            // 이미 말이 존재함
            if (node.y + value < matrix[node.x].length) {

                if (visited[node.x][node.y + value]) continue;

                // 25
                if (matrix[node.x][node.y + value] == 25) {
                    if (visited[1][9] || visited[2][13] || visited[3][19]) continue;
                }

                // 30
                if (matrix[node.x][node.y + value] == 30 && node.x != 0) {
                    if (visited[1][10] || visited[2][14] || visited[3][20]) continue;
                }

                // 35
                if (matrix[node.x][node.y + value] == 35) {
                    if (visited[1][11] || visited[2][15] || visited[3][21]) continue;
                }

                // 40
                if (matrix[node.x][node.y + value] == 40) {
                    if (visited[0][20] || visited[1][12] || visited[2][16] || visited[3][22]) continue;
                }

            }

            visited[node.x][node.y] = false;
            node.y += value;

            int tmp = 0;
            if (node.y < matrix[node.x].length) {
                visited[node.x][node.y] = true;
                tmp = matrix[node.x][node.y];
            } else {
                node.inField = false;
            }

            solve(count + 1, sum + tmp);

            if (node.inField) visited[node.x][node.y] = false;
            node.y -= value;
            visited[node.x][node.y] = true;
            node.inField = true;

        }

    }

}
