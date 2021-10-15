package na.ilseong;

import java.util.*;
import java.io.*;

public class BOJ17135 {

    static int N, M, D;
    static int[][] matrix;
    static int[][] original;
    static boolean[][] isTarget;
    static ArrayList<Integer> archer = new ArrayList<>();

    static int ANSWER = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        original = new int[N][M];
        matrix = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                original[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < M - 2; i++) {
            for (int j = i + 1; j < M - 1; j++) {
                for (int k = j + 1; k < M; k++) {

                    archer.clear();
                    archer.add(i);
                    archer.add(j);
                    archer.add(k);

                    copy();
                    attack();

                }
            }
        }

        System.out.println(ANSWER);
    }

    private static void attack() {

        isTarget = new boolean[N][M];

        int idx = N - 1;
        while (idx >= 0) {

            for (Integer c1 : archer) {

                int tmp_r2 = 16;
                int tmp_c2 = 16;
                int tmp_distance = 11;

                for (int r2 = idx; r2 > idx - D; r2--) {
                    for (int c2 = 0; c2 < M; c2++) {

                        if (r2 < 0) continue;
                        if (matrix[r2][c2] == 0) continue;

                        int newDistance = distance(idx + 1, c1, r2, c2);

                        // 사거리 밖
                        if (newDistance > D) continue;

                        // 타겟보다 멀리 있음
                        if (newDistance > tmp_distance) continue;

                        // 거리가 같지만 타겟보다 오른쪽에 있음
                        if (newDistance == tmp_distance && tmp_c2 < c2) continue;

                        tmp_r2 = r2;
                        tmp_c2 = c2;
                        tmp_distance = newDistance;

                    }
                }

                if (tmp_distance > D) continue;
                isTarget[tmp_r2][tmp_c2] =  true;
            }

            // 화살을 맞은 몬스터 처리
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (isTarget[i][j]) {
                        matrix[i][j] = 0;
                    }
                }
            }

            // 성에 들어온 몬스터 처리
            for (int y = 0; y < M; y++) {
                matrix[idx][y] = 0;
            }

            idx--;
        }

        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (isTarget[i][j]) count++;
            }
        }

        ANSWER = Math.max(ANSWER, count);

    }

    private static void copy() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                matrix[i][j] = original[i][j];
            }
        }
    }

    private static int distance(int r1, int c1, int r2, int c2) {
        return Math.abs(r1 - r2) + Math.abs(c1 - c2);
    }

}
