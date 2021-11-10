package na.ilseong.BOJ;

import java.util.*;
import java.io.*;

public class BOJ5427 {

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static int T;
    static int w, h;
    static char[][] matrix;

    static int COUNT;
    static boolean[][] visited;
    static Queue<Node> fire;
    static Queue<Node> person;

    static StringBuilder sb = new StringBuilder();

    private static class Node {
        int x, y, count;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Node(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        while (T-- > 0) {

            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            matrix = new char[h][w];
            visited = new boolean[h][w];
            fire = new LinkedList<>();
            person = new LinkedList<>();

            for (int i = 0; i < h; i++) {
                String input = br.readLine();
                for (int j = 0; j < w; j++) {

                    matrix[i][j] = input.charAt(j);

                    if (matrix[i][j] == '@') {
                        person.offer(new Node(i, j, 0));
                    }

                    if (matrix[i][j] == '*') {
                        fire.offer(new Node(i, j));
                        visited[i][j] = true;
                    }

                }
            }

            COUNT = 1_000_001;
            bfs();

            if (COUNT != 1_000_001) sb.append(COUNT);
            else sb.append("IMPOSSIBLE");
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static void bfs() {

        while (!person.isEmpty()) {

            // 불 지피기
            int size = fire.size();
            while (size-- > 0) {

                Node node = fire.poll();

                for (int i = 0; i < 4; i++) {

                    int nx = node.x + dx[i];
                    int ny = node.y + dy[i];

                    if (nx < 0 || nx >= h || ny < 0 || ny >= w) continue;

                    if (matrix[nx][ny] == '#') continue;

                    if (matrix[nx][ny] == '*' || visited[nx][ny]) continue;

                    matrix[nx][ny] = '*';
                    visited[nx][ny] = true;
                    fire.offer(new Node(nx, ny));

                }

            }

            // 사람 이동
            int personSize = person.size();
            while (personSize-- > 0) {

                Node pers = person.poll();

                for (int i = 0; i < 4; i++) {

                    int nx = pers.x + dx[i];
                    int ny = pers.y + dy[i];

                    if (nx < 0 || nx >= h || ny < 0 || ny >= w) {
                        COUNT = Math.min(COUNT, pers.count + 1);
                        continue;
                    }

                    if (matrix[nx][ny] == '*' || matrix[nx][ny] == '#') continue;

                    if (visited[nx][ny]) continue;

                    person.offer(new Node(nx, ny, pers.count + 1));
                    visited[nx][ny] = true;
//                    matrix[nx][ny] = '@';
                }
            }

//            print();
        }
    }

    private static void print() {
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
