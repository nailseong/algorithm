package na.ilseong.BOJ;

import java.util.*;
import java.io.*;

public class BOJ9328 {

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static int T, H, W;
    static ArrayList<Character> key;
    static char[][] matrix;
    static boolean[][] visited;
    static Queue<Node> queue;
    static boolean oneMore;

    static int ANSWER;

    private static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        while (T-- > 0) {

            inputData(br);

            ANSWER = 0;

            oneMore = true;
            while (oneMore) {

                oneMore = false;
                visited = new boolean[H + 2][W + 2];

                bfs(0, 0);

            }


            System.out.println(ANSWER);
        }
    }

    private static void bfs(int x, int y) {

        queue = new LinkedList<>();

        search(x, y);

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            int nx, ny;
            for (int dir = 0; dir < 4; dir++) {

                nx = node.x + dx[dir];
                ny = node.y + dy[dir];

                if (nx >= H + 2 || nx < 0 || ny >= W + 2 || ny < 0) continue;

                if (matrix[nx][ny] == '*') continue;

                if (visited[nx][ny]) continue;

                search(nx, ny);

            }

        }

    }

    private static void search(int x, int y) {
        if (matrix[x][y] == '.') { // 빈 공간 발견
            visited[x][y] = true;
            queue.offer(new Node(x, y));
        }

        if (matrix[x][y] == '$') { // 문서 발견
            ANSWER++;
            matrix[x][y] = '.';
            visited[x][y] = true;
            queue.offer(new Node(x, y));
        }

        if (Character.isUpperCase(matrix[x][y])) { // 자물쇠 발견
            for (Character k : key) {
                if (k == matrix[x][y]) {
                    matrix[x][y] = '.';
                    visited[x][y] = true;
                    queue.offer(new Node(x, y));
                }
            }
        }

        if (Character.isLowerCase(matrix[x][y])) { // 열쇠 발견
            key.add(Character.toUpperCase(matrix[x][y]));
            matrix[x][y] = '.';
            visited[x][y] = true;
            queue.offer(new Node(x, y));
            oneMore = true;
        }
    }

    // 데이터 입력 받기
    private static void inputData(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        matrix = new char[H + 2][W + 2];
        for (int i = 0; i < H; i++) {
            String input = br.readLine();
            for (int j = 0; j < W; j++) {
                matrix[i + 1][j + 1] = input.charAt(j);
            }
        }

        for (int i = 0; i < H + 2; i++) {
            matrix[i][0] = '.';
            matrix[i][W + 1] = '.';
        }
        for (int i = 0; i < W + 2; i++) {
            matrix[0][i] = '.';
            matrix[H + 1][i] = '.';
        }

        String input = br.readLine();
        key = new ArrayList<>();
        if (!input.equals("0")) {
            for (int i = 0; i < input.length(); i++) {
                key.add(Character.toUpperCase(input.charAt(i)));
            }
        }
    }

}
