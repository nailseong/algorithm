package na.ilseong;

import java.util.*;
import java.io.*;

public class BOJ13460 {

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static int N, M;
    static char[][] matrix;
    static boolean[][][][] visited;
    static Position firstPosition = new Position(0, 0, 0, 0, 1);
    static int holeX, holeY;

    private static class Position {
        int rx, ry, bx, by, count;

        public Position(int rx, int ry, int bx, int by, int count) {
            this.rx = rx;
            this.ry = ry;
            this.bx = bx;
            this.by = by;
            this.count = count;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        matrix = new char[N][M];
        visited = new boolean[N][M][N][M];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {

                char c = input.charAt(j);
                matrix[i][j] = c;

                if (c == 'R') {
                    firstPosition.rx = i;
                    firstPosition.ry = j;
                } else if (c == 'B') {
                    firstPosition.bx = i;
                    firstPosition.by = j;
                } else if (c == 'O') {
                    holeX = i;
                    holeY = j;
                }

            }
        }

        System.out.println(solve());
    }

    private static int solve() {

        Queue<Position> queue = new LinkedList<>();
        queue.offer(firstPosition);
        visited[firstPosition.rx][firstPosition.ry][firstPosition.bx][firstPosition.by] = true;

        while (!queue.isEmpty()) {

            Position current = queue.poll();

            if (current.count > 10) { // 이동 횟수 10 초과
                return -1;
            }

            int cRx = current.rx;
            int cRy = current.ry;
            int cBx = current.bx;
            int cBy = current.by;

            for (int i = 0; i < 4; i++) {

                int nRx = cRx;
                int nRy = cRy;
                int nBx = cBx;
                int nBy = cBy;

                boolean isHole_red = false;
                boolean isHole_blue = false;

                // Red 이동
                while (matrix[nRx + dx[i]][nRy + dy[i]] != '#') {

                    nRx += dx[i];
                    nRy += dy[i];

                    if (nRx == holeX && nRy == holeY) {
                        isHole_red = true;
                        break;
                    }

                }

                // Blue 이동
                while (matrix[nBx + dx[i]][nBy + dy[i]] != '#') {

                    nBx += dx[i];
                    nBy += dy[i];

                    if (nBx == holeX && nBy == holeY) {
                        isHole_blue = true;
                        break;
                    }

                }

                // Blue 골인
                if (isHole_blue) continue;

                // Red 골인
                if (isHole_red) return current.count;

                // 구슬이 겹침
                if (nRx == nBx && nRy == nBy) {
                    if (i == 0) { // 아래로 기울인 경우
                        if (cRx > cBx) { // Red가 아래
                            nBx -= dx[i]; // Blue 이동
                        } else { // Blue가 아래
                            nRx -= dx[i]; // Red 이동
                        }
                    } else if (i == 1) { // 위로 기울인 경우
                        if (cRx > cBx) { // Red가 아래
                            nRx -= dx[i]; // Red 이동
                        } else { // Blue가 아래
                            nBx -= dx[i]; // Blue 이동
                        }
                    } else if (i == 2) { // 오른쪽으로 기울인 경우
                        if (cRy > cBy) { // Red가 오른쪽
                            nBy -= dy[i]; // Blue 이동
                        } else { // Blue가 오른쪽
                            nRy -= dy[i]; // Red 이동
                        }
                    } else if (i == 3) { // 왼쪽으로 기울인 경우
                        if (cRy > cBy) { // Red가 오른쪽
                            nRy -= dy[i]; // Red 이동
                        } else { // Blue가 오른쪽
                            nBy -= dy[i]; // Blue 이동
                        }
                    }

                }

                if (visited[nRx][nRy][nBx][nBy]) continue;

                visited[nRx][nRy][nBx][nBy] = true;
                queue.offer(new Position(nRx, nRy, nBx, nBy, current.count + 1));
            }

        }

        return -1;
    }

}
