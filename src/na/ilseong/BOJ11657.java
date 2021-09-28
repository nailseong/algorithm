package na.ilseong;

import java.util.*;
import java.io.*;

public class BOJ11657 {

    static int INF = 1_000_000_000;

    static int N, M;
    static int A, B, C;

    static ArrayList<ArrayList<Node>> matrix;
    static long[] dist;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder ANSWER = new StringBuilder();

    public static void main(String[] args) throws IOException {
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dist = new long[N + 1];
        Arrays.fill(dist, INF);
        dist[1] = 0;

        matrix = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            matrix.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            matrix.get(A).add(new Node(B, C));
        }

        boolean isCycle = bellmanFord();
        if (isCycle) {
            System.out.println(-1);
        } else {
            for (int i = 2; i < N + 1; i++) {
                ANSWER.append(dist[i] < INF ? dist[i] : "-1");
                ANSWER.append("\n");
            }
            System.out.print(ANSWER);
        }
    }

    private static boolean bellmanFord() {
        boolean isUpdate = false;

        for (int i = 1; i < N; i++) {
            isUpdate = false;

            for (int j = 1; j < N + 1; j++) {
                for (Node node : matrix.get(j)) {
                    if (dist[j] != INF && dist[node.end] > dist[j] + node.cost) {
                        dist[node.end] = dist[j] + node.cost;
                        isUpdate = true;
                    }
                }
            }

            if (!isUpdate) break;
        }

        if (isUpdate) {
            for (int i = 1; i < N + 1; i++) {
                for (Node node : matrix.get(i)) {
                    if (dist[i] != INF && dist[node.end] > dist[i] + node.cost) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private static class Node {

        int end;
        int cost;

        public Node(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }

    }

}
