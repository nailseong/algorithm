package na.ilseong.BOJ.BOJ11000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ11724 {

    static BufferedWriter bw;
    static int N;
    static int M;
    static boolean[][] matrix;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {

        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        matrix = new boolean[N + 1][N + 1];
        visited = new boolean[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            matrix[a][b] = true;
            matrix[b][a] = true;
        }

        int answer = 0;
        while (isEnd() != -1) {
            answer++;
            bfs(isEnd());
        }

        bw.write(answer + "");

        bw.flush();
        bw.close();
    }

    private static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;

        while (!q.isEmpty()) {
            int current = q.poll();

            for (int i = 1; i <= N; i++) {
                if (visited[i]) {
                    continue;
                }
                if (!matrix[current][i]) {
                    continue;
                }
                q.add(i);
                visited[i] = true;
            }
        }
    }

    private static int isEnd() {
        int result = -1;
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                result = i;
                break;
            }
        }
        return result;
    }
}
