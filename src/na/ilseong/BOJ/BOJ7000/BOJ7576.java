package na.ilseong.BOJ.BOJ7000;

import java.util.*;
import java.io.*;

public class BOJ7576 {

	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};

	static int[][] matrix;
	static boolean[][] visited;
	static Queue<Integer> q = new LinkedList<>();
	static int n;
	static int m;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		matrix = new int[n][m];
		visited = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (!visited[i][j] && matrix[i][j] == 1) {
					q.offer(i);
					q.offer(j);
				}
			}
		}

		bfs();

		System.out.println(findAnswer());
	}

	private static void bfs() {
		while (!q.isEmpty()) {
			int x = q.poll();
			int y = q.poll();
			int nextDay = matrix[x][y] + 1;
			visited[x][y] = true;

			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (nx >= n || nx < 0 || ny >= m || ny < 0 || visited[nx][ny] || matrix[nx][ny] == -1 || matrix[nx][ny] == matrix[x][y]) {
					continue;
				}
				matrix[nx][ny] = nextDay;
				visited[nx][ny] = true;
				q.offer(nx);
				q.offer(ny);
			}
		}
	}

	private static int findAnswer() {
		int answer = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (matrix[i][j] == 0) {
					return -1;
				}
				if (matrix[i][j] > answer) {
					answer = matrix[i][j];
				}
			}
		}

		return answer - 1;
	}
}

