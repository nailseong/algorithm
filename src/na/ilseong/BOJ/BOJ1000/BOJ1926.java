package na.ilseong.BOJ.BOJ1000;

import java.util.*;
import java.io.*;

public class BOJ1926 {

	static int n;
	static int m;
	static boolean[][] matrix;
	static boolean[][] visited;
	static int count = 0;
	static int maxSize = 0;
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		matrix = new boolean[n][m];
		visited = new boolean[n][m];
		for (int x = 0; x < n; x++) {
			st = new StringTokenizer(br.readLine());
			for (int y = 0; y < m; y++) {
				int tmp = Integer.parseInt(st.nextToken());
				if (tmp == 1) {
					matrix[x][y] = true;
				}
			}
		}

		for (int x = 0; x < n; x++) {
			for (int y = 0; y < m; y++) {
				if (!matrix[x][y]|| visited[x][y]) {
					continue;
				}
				bfs(x, y);
			}
		}

		System.out.println(count);
		System.out.println(maxSize);
	}

	private static void bfs(int x, int y) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(x);
		q.offer(y);
		visited[x][y] = true;
		int tmpSize = 1;
		count++;

		while (!q.isEmpty()) {
			int nx = q.poll();
			int ny = q.poll();

			for (int i = 0; i < 4; i++) {
				int xx = nx + dx[i];
				int yy = ny + dy[i];
				if (xx >= n || xx < 0) {
					continue;
				}
				if (yy >= m || yy < 0) {
					continue;
				}
				if (visited[xx][yy]) {
					continue;
				}
				if (!matrix[xx][yy]) {
					continue;
				}
				tmpSize++;
				visited[xx][yy] = true;
				q.offer(xx);
				q.offer(yy);
			}
		}

		if (tmpSize > maxSize) {
			maxSize = tmpSize;
		}
	}
}

