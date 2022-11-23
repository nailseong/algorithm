package na.ilseong.BOJ.BOJ15000;

import java.io.*;
import java.util.*;

public class BOJ15683 {

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int EMPTY = 0;
    static int WALL = 6;
    static int WATCHING = -1;

    static int[][] matrix = new int[8][8];
    static int[] cx = new int[8];
    static int[] cy = new int[8];
    static int[] cctvs = new int[8];
    static int cctvCount = 0;
    static int n;
    static int m;

    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                final int num = Integer.parseInt(st.nextToken());
                matrix[i][j] = num;
                if (num >= 1 && num <= 5) {
                    cx[cctvCount] = i;
                    cy[cctvCount] = j;
                    cctvs[cctvCount] = num;
                    cctvCount++;
                }
            }
        }

        if (cctvCount != 0) recursive(0);
        else count();

        System.out.println(answer);
    }

    private static void recursive(int idx) {
        if (idx == cctvCount) {
            count();
            return;
        }

        int x = cx[idx];
        int y = cy[idx];
        int cctv = cctvs[idx];

        if (cctv == 1) {
            for (int i = 0; i < 4; i++) {
                final Queue<Integer> q = watch(x, y, i);

                recursive(idx + 1);

                unWatch(q);
            }
        }

        if (cctv == 2) {
            for (int i = 0; i < 2; i++) {
                final Queue<Integer> q1 = watch(x, y, i);
                final Queue<Integer> q2 = watch(x, y, i + 2);

                recursive(idx + 1);

                unWatch(q1);
                unWatch(q2);
            }
        }

        if (cctv == 3) {
            for (int i = 0; i < 4; i++) {
                final Queue<Integer> q1 = watch(x, y, i);
                final Queue<Integer> q2 = watch(x, y, (i + 1) % 4);

                recursive(idx + 1);

                unWatch(q1);
                unWatch(q2);
            }
        }

        if (cctv == 4) {
            for (int i = 0; i < 4; i++) {
                final Queue<Integer> q1 = watch(x, y, i);
                final Queue<Integer> q2 = watch(x, y, (i + 1) % 4);
                final Queue<Integer> q3 = watch(x, y, (i + 2) % 4);

                recursive(idx + 1);

                unWatch(q1);
                unWatch(q2);
                unWatch(q3);
            }
        }

        if (cctv == 5) {
            final Queue<Integer> q1 = watch(x, y, 0);
            final Queue<Integer> q2 = watch(x, y, 1);
            final Queue<Integer> q3 = watch(x, y, 2);
            final Queue<Integer> q4 = watch(x, y, 3);

            recursive(idx + 1);

            unWatch(q1);
            unWatch(q2);
            unWatch(q3);
            unWatch(q4);
        }
    }

    private static Queue<Integer> watch(int x, int y, int dir) {
        Queue<Integer> q = new LinkedList<>();
        int nx = x + dx[dir];
        int ny = y + dy[dir];
        while (nx >= 0 && nx < n && ny >= 0 && ny < m && matrix[nx][ny] != WALL) {
            if (matrix[nx][ny] == EMPTY) {
                matrix[nx][ny] = WATCHING;
                q.offer(nx);
                q.offer(ny);
            }
            nx += dx[dir];
            ny += dy[dir];
        }
        return q;
    }

    private static void unWatch(Queue<Integer> q) {
        while (!q.isEmpty()) {
            int x = q.poll();
            int y = q.poll();
            matrix[x][y] = EMPTY;
        }
    }

    private static void count() {
        int tmp = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == EMPTY) tmp++;
            }
        }
        if (tmp < answer) answer = tmp;
    }
}
