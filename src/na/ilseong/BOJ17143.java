package na.ilseong;

import java.util.*;
import java.io.*;

public class BOJ17143 {

    static int[] dr = {0, -1, 1, 0, 0};
    static int[] dc = {0, 0, 0, 1, -1};

    static int R, C, M;
    static Shark[][] matrix;

    static int ANSWER = 0;

    private static class Shark {
        private final int r, c, s, d, z;

        public Shark(int r, int c, int s, int d, int z) {
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        if (M == 0) {
            System.out.println("0");
            return;
        }

        matrix = new Shark[R + 1][C + 1];
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            matrix[r][c] = new Shark(r, c, s, d, z);
        }

        int position = 1;
        while (position <= C) {

            fishing(position);

            move();

            position++;

        }

        System.out.println(ANSWER);
    }

    private static void fishing(int position) {
        for (int i = 1; i <= R; i++) {
            if (matrix[i][position] != null) {
                ANSWER += matrix[i][position].z;
                matrix[i][position] = null;
                return;
            }
        }
    }

    private static void move() {

        Shark[][] newMatrix = new Shark[R + 1][C + 1];

        for (int r = 1; r <= R; r++) {
            for (int c = 1; c <= C; c++) {

                if (matrix[r][c] == null) continue;

                assert matrix[r][c] != null;
                Shark shark = matrix[r][c];

                int newR = shark.r;
                int newC = shark.c;
                int newDir = shark.d;
                int count = shark.s;

                // 이동 시키기
                while (count > 0) {

                    int nr = newR + dr[newDir];
                    int nc = newC + dc[newDir];

                    // 이동 X, 방향 전환
                    if (nr < 1 || nr > R || nc < 1 || nc > C) {
                        if (newDir == 1) newDir = 2;
                        else if (newDir == 2) newDir = 1;
                        else if (newDir == 3) newDir = 4;
                        else if (newDir == 4) newDir = 3;
                        continue;
                    }

                    newR = nr;
                    newC = nc;

                    count--;
                }

                // 빈 공간
                if (newMatrix[newR][newC] == null) {
                    newMatrix[newR][newC] = new Shark(newR, newC, shark.s, newDir, shark.z);
                }

                // 크기가 더 큰 상어 존재
                if (newMatrix[newR][newC].z > shark.z) continue;

                newMatrix[newR][newC] = new Shark(newR, newC, shark.s, newDir, shark.z);

            }
        }

        // 새로운 메트릭스로 교체
        matrix = newMatrix;
    }

}
