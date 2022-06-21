package na.ilseong.BOJ.BOJ2000;

import java.util.*;
import java.io.*;

public class BOJ2239 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int[][] sudoko = new int[9][9];
    static ArrayList<Coordinate> list = new ArrayList<>();

    static boolean isFinish = false;

    private static class Coordinate {

        int x;
        int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 9; i++) {
            String s = br.readLine();
            for (int j = 0; j < 9; j++) {
                sudoko[i][j] = s.charAt(j) - '0';
                if (sudoko[i][j] == 0) {
                    list.add(new Coordinate(i, j));
                }
            }
        }

        dfs(0);
    }

    private static void dfs(int idx) {

        if (list.size() == idx) { // 종료 체크
            isFinish = true;
            printSudoku();
            return;
        }

        if (isFinish) return;

        Coordinate current = list.get(idx);
        for (int i = 1; i <= 9; i++) { // 1 ~ 9 넣어 보기
            if (sudoko[current.x][current.y] == 0 && check(current, i)) { // 숫자가 0이고 넣을 수 있을 때
                sudoko[current.x][current.y] = i; // 숫자 넣기
                dfs(idx + 1);
                sudoko[current.x][current.y] = 0; // 숫자 롤백
            }
        }

    }

    private static boolean check(Coordinate coord, int value) {
        for (int i = 0; i < 9; i++) { // row
            if (sudoko[coord.x][i] == value) return false;
        }

        for (int i = 0; i < 9; i++) { // column
            if (sudoko[i][coord.y] == value) return false;
        }

        // nx, ny
        // 0, 1, 2 -> 0 -> 0
        // 3, 4, 5 -> 1 -> 3
        // 6, 7, 8 -> 2 -> 6

        // block
        int x = (coord.x / 3) * 3;
        int y = (coord.y / 3) * 3;
        for (int i = x; i < x + 3; i++) {
            for (int j = y; j < y + 3; j++) {
                if (sudoko[i][j] == value) return false;
            }
        }

        return true;
    }

    static private void printSudoku() {
        for (int[] ints : sudoko) {
            for (int anInt : ints) {
                System.out.print(anInt);
            }
            System.out.println();
        }
    }

}
