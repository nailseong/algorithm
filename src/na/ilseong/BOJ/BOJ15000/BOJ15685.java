package na.ilseong.BOJ.BOJ15000;

import java.util.*;
import java.io.*;

public class BOJ15685 {

    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {1, 0, -1, 0};

    static int N;
    static boolean[][] matrix;
    static ArrayList<Curve> curves = new ArrayList<>();

    static int ANSWER = 0;

    private static class Curve {
        int x, y, dir, gen;

        public Curve(int x, int y, int dir, int gen) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.gen = gen;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        matrix = new boolean[101][101];
        N = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            curves.add(new Curve(y, x, d, g));
        }
        for (Curve curve : curves) {
            draw(curve);
        }

        calculate();

        System.out.println(ANSWER);
    }

    private static void draw(Curve curve) {

        int[] plan = new int[((int) Math.pow(2, curve.gen))];

        plan[0] = curve.dir;
        for (int gen = 1; gen <= curve.gen; gen++) {
            int length = (int) Math.pow(2, gen);

            for (int idx = length / 2; idx < length; idx++) {
                plan[idx] = (plan[length - 1 - idx] + 1) % 4;
            }

        }

        int current_x = curve.x;
        int current_y = curve.y;
        matrix[current_x][current_y] = true;
        for (int dir : plan) {

            int nx = current_x + dx[dir];
            int ny = current_y + dy[dir];
            matrix[nx][ny] = true;

            current_x = nx;
            current_y = ny;

        }

    }

    private static void calculate() {
        for (int i = 0; i <= 99; i++) {
            for (int j = 0; j <= 99; j++) {
                if (matrix[i][j] && matrix[i + 1][j] && matrix[i][j + 1] && matrix[i + 1][j + 1]) {
                    ANSWER++;
                }
            }
        }
    }

}
