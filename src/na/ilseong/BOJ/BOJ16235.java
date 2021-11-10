package na.ilseong.BOJ;

import java.util.*;
import java.io.*;

public class BOJ16235 {

    static int[] dx = {1, -1, 0, 0, 1, 1, -1, -1};
    static int[] dy = {0, 0, 1, -1, 1, -1, 1, -1};

    static int N, M, K;
    static int[][] matrix;
    static Node[][] farm;

    private static class Node {
        int value;
        PriorityQueue<Integer> trees;

        public Node() {
            this.value = 5;
            this.trees = new PriorityQueue<>();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        matrix = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        farm = new Node[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                farm[i][j] = new Node();
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            farm[x][y].trees.add(z);
        }

        solve();

        int answer = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                answer += farm[i][j].trees.size();
            }
        }

        System.out.println(answer);
    }

    private static void solve() {
        while (K-- > 0) {

            // 봄 & 여름
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    Node node = farm[i][j];

                    if (node.trees.size() == 0) continue;

                    int tmp = 0;
                    PriorityQueue<Integer> newTrees = new PriorityQueue<>();

                    while (!node.trees.isEmpty()) {

                        Integer age = node.trees.poll();

                        if (age <= node.value) { // 양분 먹음
                            node.value -= age;
                            newTrees.add(age + 1);
                        } else { // 죽는 나무 -> 여름에 양분으로 전환
                            tmp += age / 2;
                        }

                    }

                    node.trees = newTrees;
                    node.value += tmp;
                }
            }

            // 가을
            for (int x = 1; x <= N; x++) {
                for (int y = 1; y <= N; y++) {

                    Node node = farm[x][y];

                    if (node.trees.size() == 0) continue;

                    for (Integer age : node.trees) {

                        if (age % 5 != 0) continue;

                        for (int k = 0; k < 8; k++) {
                            int nx = x + dx[k];
                            int ny = y + dy[k];

                            if (nx < 1 || nx > N || ny < 1 || ny > N) continue;

                            farm[nx][ny].trees.add(1);

                        }

                    }
                }
            }

            // 겨울
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    farm[i][j].value += matrix[i][j];
                }
            }

        }
    }
}
