package na.ilseong.BOJ.BOJ14000;

import java.util.*;
import java.io.*;

public class BOJ14938 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int INF = 1_000_000_000;

    static int N, M, R;
    static int A, B, I;
    static int[] items;

    static int[][] graph;

    static int ANSWER = 0;

    public static void main(String[] args) throws IOException {
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        items = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            items[i] = Integer.parseInt(st.nextToken());
        }

        graph = new int[N + 1][N + 1];
        for (int i = 0; i < N + 1; i++) {
            Arrays.fill(graph[i], INF);
            graph[i][i] = 0;
        }

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());

            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            I = Integer.parseInt(st.nextToken());

            graph[A][B] = I;
            graph[B][A] = I;
        }

        floydWarshall();

        int count;
        for (int i = 1; i <= N; i++) {
            count = 0;
            for (int j = 1; j <= N; j++) {
                if (graph[i][j] <= M) {
                    count += items[j];
                }
            }
            ANSWER = Math.max(ANSWER, count);
        }

        System.out.println(ANSWER);
    }

    private static void floydWarshall() {
        for (int k = 1; k < N + 1; k++) {
            for (int i = 1; i < N + 1; i++) {
                for (int j = 1; j < N + 1; j++) {
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }
    }

}
