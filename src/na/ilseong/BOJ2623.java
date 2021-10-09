package na.ilseong;

import java.util.*;
import java.io.*;

public class BOJ2623 {

    static int N, M;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static int[] edge;
    static boolean[] visited;

    static Queue<Integer> queue = new LinkedList<>();

    static boolean isAble = true;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        edge = new int[N + 1];
        visited = new boolean[N + 1];

        while (M-- > 0) {

            st = new StringTokenizer(br.readLine());
            int size = Integer.parseInt(st.nextToken());
            int[] list = new int[size];

            for (int i = 0; i < size; i++) {
                list[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 1; i < size; i++) {
                graph.get(list[i - 1]).add(list[i]);
                edge[list[i]]++;
            }

        }

        for (int i = 1; i <= N; i++) {
            if (edge[i] == 0) {
                queue.offer(i);
                visited[i] = true;
            }
        }

        while (!queue.isEmpty()) {

            Integer node = queue.poll();
            sb.append(node + "\n");

            ArrayList<Integer> edges = graph.get(node);
            for (Integer e : edges) {
                edge[e]--;
            }

            for (int i = 1; i <= N; i++) {
                if (edge[i] == 0 && !visited[i]) {
                    queue.offer(i);
                    visited[i] = true;
                }
            }

        }

        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                isAble = false;
                break;
            }
        }

        if (isAble) {
            System.out.println(sb);
        } else {
            System.out.println("0");
        }
    }

}
