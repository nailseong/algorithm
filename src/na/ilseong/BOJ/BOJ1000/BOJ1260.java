package na.ilseong.BOJ.BOJ1000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1260 {

    static BufferedWriter bw;
    static boolean[][] matrix;
    static int n;

    public static void main(String[] args) throws Exception {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());

        matrix = new boolean[n + 1][n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            matrix[a][b] = true;
            matrix[b][a] = true;
        }

        boolean[] visited = new boolean[n + 1];
        dfs(visited, v);
        bw.write("\n");

        visited = new boolean[n + 1];
        bfs(visited, v);

        bw.flush();
        bw.close();
    }

    private static void dfs(boolean[] visited, int idx) throws Exception {
        bw.write(idx + " ");
        visited[idx] = true;
        for (int i = 1; i <= n; i++) {
            if (visited[i]) {
                continue;
            }
            if (!matrix[idx][i]) {
                continue;
            }
            dfs(visited, i);
        }
    }

    private static void bfs(boolean[] visited, int idx) throws Exception {
        Queue<Integer> q = new LinkedList<>();
        q.add(idx);
        visited[idx] = true;
        bw.write(idx + " ");

        while(!q.isEmpty()) {
            int next = q.poll();

            for (int i = 1; i <= n; i++) {
                if (visited[i]) {
                    continue;
                }
                if (!matrix[next][i]) {
                    continue;
                }
                q.add(i);
                visited[i] = true;
                bw.write(i + " ");
            }
        }
    }
}
