package na.ilseong.BOJ.BOJ18000;

import java.util.*;
import java.io.*;

public class BOJ18808 {

    static boolean[][] matrix = new boolean[40][40];
    static List<boolean[][]> stickers = new ArrayList<>();

    static int n;
    static int m;
    static int k;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            boolean[][] sticker = new boolean[r][c];
            for (int j = 0; j < r; j++) {
                st = new StringTokenizer(br.readLine());
                for (int t = 0; t < c; t++) {
                    sticker[j][t] = Integer.parseInt(st.nextToken()) == 1;
                }
            }
            stickers.add(sticker);
        }

        for (boolean[][] sticker : stickers) {
            addSticker(sticker);
        }

        System.out.println(getAnswer());
    }

    private static void addSticker(boolean[][] sticker) {
        for (int i = 0; i < 4; i++) {
            for (int x = 0; x < n; x++) {
                for (int y = 0; y < m; y++) {
                    if (!canAddSticker(sticker, x, y)) continue;
                    addSticker(sticker, x, y);
                    return;
                }
            }
            sticker = rotate(sticker);
        }
    }

    private static int getAnswer() {
        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j]) result++;
            }
        }
        return result;
    }

    private static boolean canAddSticker(boolean[][] sticker, int x, int y) {
        if (x + sticker.length > n || y + sticker[0].length > m) return false;
        for (int i = 0; i < sticker.length; i++) {
            for (int j = 0; j < sticker[0].length; j++) {
                if (sticker[i][j] && matrix[x + i][y + j]) return false;
            }
        }
        return true;
    }

    private static void addSticker(boolean[][] sticker, int x, int y) {
        for (int i = 0; i < sticker.length; i++) {
            for (int j = 0; j < sticker[0].length; j++) {
                if (!sticker[i][j]) continue;
                matrix[x + i][y + j] = true;
            }
        }
    }

    private static boolean[][] rotate(boolean[][] sticker) {
        int x = sticker[0].length;
        int y = sticker.length;
        boolean[][] rotatedSticker = new boolean[x][y];
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                rotatedSticker[j][y - 1 - i] = sticker[i][j];
            }
        }
        return rotatedSticker;
    }
}
