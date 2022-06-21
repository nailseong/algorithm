package na.ilseong.BOJ.BOJ11000;

import java.util.*;
import java.io.*;

public class BOJ11559 {

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static char[][] matrix = new char[12][6];
    static boolean[][] visited;
    static ArrayList<Stack<Node>> Puyo;

    static int COUNT = 0;

    private static class Node {
        int x, y;
        char color;

        public Node(int x, int y, char color) {
            this.x = x;
            this.y = y;
            this.color = color;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 12; i++) {
            String input = br.readLine();
            for (int j = 0; j < 6; j++) {
                matrix[i][j] = input.charAt(j);
            }
        }

        // R, G, B, P, Y
        Puyo = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Puyo.add(new Stack<>());
        }

        solve();

        System.out.println(COUNT);
    }

    private static void solve() {

        // BFS (Puyo에 Node를 집어넣기)
        visited = new boolean[12][6];
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 6; j++) {
                if (matrix[i][j] != '.' && !visited[i][j]) {
                    bfs(i, j);
                }
            }
        }

        while (true) {

            boolean isFinish = true;
            for (Stack<Node> stack : Puyo) {
                if (!stack.isEmpty()) {
                    isFinish = false;
                    break;
                }
            }

            if (isFinish) break;


            // 터뜨리기 -> COUNT++
            for (Stack<Node> stack : Puyo) {
                while (!stack.isEmpty()) {
                    Node puyo = stack.pop();
                    matrix[puyo.x][puyo.y] = '.';
                }
            }
            COUNT++;

            // 뿌요 정렬
            sortPuyo();

            // BFS
            visited = new boolean[12][6];
            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    if (matrix[i][j] != '.' && !visited[i][j]) {
                        bfs(i, j);
                    }
                }
            }

        }

    }

    private static void bfs(int x, int y) {

        Queue<Node> queue = new LinkedList<>();

        Stack<Node> currentPuyo = null;
        if (matrix[x][y] == 'R') {
            currentPuyo = Puyo.get(0);
        } else if (matrix[x][y] == 'G') {
            currentPuyo = Puyo.get(1);
        } else if (matrix[x][y] == 'B') {
            currentPuyo = Puyo.get(2);
        } else if (matrix[x][y] == 'P') {
            currentPuyo = Puyo.get(3);
        } else if (matrix[x][y] == 'Y') {
            currentPuyo = Puyo.get(4);
        }

        assert currentPuyo != null;
        Node currentNode = new Node(x, y, matrix[x][y]);

        currentPuyo.push(currentNode);
        queue.offer(currentNode);
        visited[x][y] = true;

        int count = 1;
        while (!queue.isEmpty()) {

            Node node = queue.poll();

            for (int i = 0; i < 4; i++) {

                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                if (nx < 0 || nx >= 12 || ny < 0 || ny >= 6) continue;

                if (matrix[nx][ny] != node.color) continue;

                if (visited[nx][ny]) continue;

                Node nextNode = new Node(nx, ny, node.color);
                currentPuyo.push(nextNode);
                queue.offer(nextNode);
                visited[nx][ny] = true;
                count++;
            }

        }

        if (count < 4) {
            // 4개 이하면 스택 비우기
            while (count-- > 0) {
                currentPuyo.pop();
            }
        }

    }

    private static void sortPuyo() {
        for (int y = 0; y < 6; y++) {

            int idx = 11;
            for (int x = 11; x >= 0; x--) {
                if (matrix[x][y] != '.') {

                    if (x != idx) {
                        matrix[idx][y] = matrix[x][y];
                        matrix[x][y] = '.';
                    }

                    idx--;
                }
            }
        }
    }
}
