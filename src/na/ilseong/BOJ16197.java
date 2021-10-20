package na.ilseong;

import java.util.*;
import java.io.*;

public class BOJ16197 {

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    static int N, M;
    static char[][] matrix;

    static boolean[][] visited;
    static Queue<Coins> queue = new LinkedList<>();

    static int ANSWER = 11;

    private static class Coins {

        Coin coin1;
        Coin coin2;
        int count;

        public Coins(Coin coin1, Coin coin2, int count) {
            this.coin1 = coin1;
            this.coin2 = coin2;
            this.count = count;
        }

    }

    private static class Coin {
        int x, y;

        public Coin(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        matrix = new char[N][M];
        visited = new boolean[N][M];
        Coin coin1 = null;
        Coin coin2 = null;

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                matrix[i][j] = input.charAt(j);
                if (matrix[i][j] == 'o') {
                    if (coin1 == null) {
                        coin1 = new Coin(i, j);
                    } else {
                        coin2 = new Coin(i, j);
                    }
                }
            }
        }

        bfs(coin1, coin2);

        System.out.println(ANSWER != 11 ? ANSWER : -1);
    }

    private static void bfs(Coin coin1, Coin coin2) {

        visited[coin1.x][coin1.y] = true;
        queue.offer(new Coins(coin1, coin2, 0));

        while (!queue.isEmpty()) {

            Coins poll = queue.poll();

            if (poll.count > 10) continue;

            // 코인이 없음
            if (poll.coin1 == null && poll.coin2 == null) continue;

            // 코인이 1개만 존재 -> 정답 계산
            if (poll.coin1 == null || poll.coin2 == null) {
                ANSWER = Math.min(ANSWER, poll.count);
                continue;
            }

            // 코인 2개 존재
            for (int i = 0; i < 4; i++) {

                int nx1 = poll.coin1.x + dx[i];
                int ny1 = poll.coin1.y + dy[i];

                int nx2 = poll.coin2.x + dx[i];
                int ny2 = poll.coin2.y + dy[i];

                boolean c1 = true;
                boolean c2 = true;
                if (nx1 < 0 || nx1 >= N || ny1 < 0 || ny1 >= M) {
                    c1 = false;
                }

                if (c1 && matrix[nx1][ny1] == '#') {
                    nx1 -= dx[i];
                    ny1 -= dy[i];
                }

                if (nx2 < 0 || nx2 >= N || ny2 < 0 || ny2 >= M) {
                    c2 = false;
                }

                if (c2 && matrix[nx2][ny2] == '#') {
                    nx2 -= dx[i];
                    ny2 -= dy[i];
                }

                // 동전 이동 안함
                if (nx1 == poll.coin1.x && ny1 == poll.coin1.y
                        && nx2 == poll.coin2.x && ny2 == poll.coin2.y ) continue;

                // 동전 겹침
                if (nx1 == nx2 && ny1 == ny2) continue;

                if (!c1 && !c2) continue;

                Coin newCoin1 = c1 ? new Coin(nx1, ny1) : null;
                Coin newCoin2 = c2 ? new Coin(nx2, ny2) : null;
                queue.offer(new Coins(newCoin1, newCoin2, poll.count + 1));
            }

        }

    }

}
