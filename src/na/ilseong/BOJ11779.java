package na.ilseong;

import java.util.*;
import java.io.*;

public class BOJ11779 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int INF = Integer.MAX_VALUE;

    static int N, M;
    static int START, END;

    static ArrayList<ArrayList<Node>> graph;
    static int[] previousNode;
    static int[] dist;
    static boolean[] visited;
    static PriorityQueue<Node> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        graph = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int START = Integer.parseInt(st.nextToken());
            int END = Integer.parseInt(st.nextToken());
            int COST = Integer.parseInt(st.nextToken());

            graph.get(START).add(new Node(END, COST));
        }

        st = new StringTokenizer(br.readLine());
        START = Integer.parseInt(st.nextToken());
        END = Integer.parseInt(st.nextToken());

        visited = new boolean[N + 1];
        previousNode = new int[N + 1];
        dist = new int[N + 1];
        Arrays.fill(dist, INF);

        dijkstra();

        Stack<Integer> stack = solve();
        System.out.println(dist[END]);
        System.out.println(stack.size());
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }

    private static void dijkstra() {

        pq.add(new Node(START, 0));
        dist[START] = 0;

        while (!pq.isEmpty()) {
            Node currentNode = pq.poll();
            int now = currentNode.end;

            if (visited[now]) continue;

            visited[now] = true;
            for (Node nextNode : graph.get(now)) {

                int next = nextNode.end;
                int cost = nextNode.cost;

                if (dist[next] >= dist[now] + cost) {
                    dist[next] = dist[now] + cost;
                    pq.add(new Node(next, dist[next]));
                    previousNode[next] = now;

                }

            }
        }

    }

    private static Stack<Integer> solve() {
        Stack<Integer> stack = new Stack<>();
        int current = END;

        while (current != START) {
            stack.push(current);
            current = previousNode[current];
        }

        stack.push(current);

        return stack;
    }

    private static class Node implements Comparable<Node> {
        int end, cost;

        @Override
        public int compareTo(Node o) {
            return cost - o.cost;
        }

        public Node(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }
    }

}
