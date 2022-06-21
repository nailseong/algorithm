package na.ilseong.BOJ.BOJ1000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1504 {

    static int N;
    static int E;
    static ArrayList<Node>[] graph;
    static int V1;
    static int V2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));
        }

        st = new StringTokenizer(br.readLine());
        V1 = Integer.parseInt(st.nextToken());
        V2 = Integer.parseInt(st.nextToken());

        int[] distance_start = dijkstra(1);
        int[] distance_v1 = dijkstra(V1);
        int[] distance_v2 = dijkstra(V2);

        int a = distance_start[V1] + distance_v1[V2] + distance_v2[N];
        int b = distance_start[V2] + distance_v2[V1] + distance_v1[N];
        int answer;
        if (a < b) {
            answer = a;
        } else {
            answer = b;
        }

        if (answer >= 2_000_000_000) {
            answer = -1;
        }
        System.out.println(answer);
    }

    private static int[] dijkstra(int startNode) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(startNode, 0));

        int[] distance = new int[N + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[startNode] = 0;

        while (!pq.isEmpty()) {
            Node currentNode = pq.poll();

            for (Node next : graph[currentNode.n]) {
                if (distance[currentNode.n] + next.w < distance[next.n]) {
                    distance[next.n] = distance[currentNode.n] + next.w;
                    pq.offer(new Node(next.n, distance[next.n]));
                }
            }
        }

        return distance;
    }


    private static class Node implements Comparable<Node> {

        int n;
        int w;

        public Node(int n, int w) {
            this.n = n;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return w - o.w;
        }

    }

}
