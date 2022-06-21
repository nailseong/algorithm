package na.ilseong.BOJ.BOJ1000;

import java.io.*;
import java.util.*;

public class BOJ1865 {

    static int TC;

    static int N;
    static int M;
    static int W;

    static int[] dist;
    static ArrayList<ArrayList<Node>> matrix;

    static int INF = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        TC = Integer.parseInt(br.readLine());

        while (TC-- > 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            dist = new int[N + 1];
            matrix = new ArrayList<>(); // 매트릭스 초기화
            for (int i = 0; i < N + 1; i++) {
                matrix.add(new ArrayList<>());
            }

            for (int i = 0; i < M; i++) { // 거리 정보 입력
                st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken());

                matrix.get(S).add(new Node(E, T));
                matrix.get(E).add(new Node(S, T));
            }

            for (int i = 0; i < W; i++) { // 웜홀 정보 입력
                st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken());

                matrix.get(S).add(new Node(E, -T));
            }

        System.out.println(bellmanFord() ? "YES" : "NO");
        }

    }

    private static boolean bellmanFord() {
        Arrays.fill(dist, INF); // 거리 정보 초기화
        dist[1] = 0; // 시작 지점 초기화
        boolean isUpdate = false;

        for (int i = 1; i < N; i++) { // (Node 개수 - 1)번 만큼 반복

            isUpdate = false;

            for (int j = 1; j < N + 1; j++) {
                for (Node node : matrix.get(j)) {
                    if (dist[node.end] > dist[j] + node.weight) {
                        // 거리가 더 짧으면 업데이트
                        dist[node.end] = dist[j] + node.weight;
                        isUpdate = true;
                    }
                }
            }

            if (!isUpdate) {
                break;
            }

        }

        if (isUpdate) {
            for (int i = 1; i < N + 1; i++) {
                for (Node node : matrix.get(i)) {
                    if (dist[node.end] > dist[i] + node.weight) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private static class Node {

        int end;
        int weight;

        public Node(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }

    }

}
