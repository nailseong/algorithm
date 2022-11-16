package na.ilseong.BOJ.BOJ4000;

import java.util.*;
import java.io.*;

public class BOJ4179 {

	static char TARGET = 'J';
	static char FIRE = 'F';
	static char WALL = '#';
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};

	static int n;
	static int m;
	static char[][] matrix;
	static boolean[][] visited;
	static Queue<Integer> q = new LinkedList<>();
	static int answer = 999_999_999;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		matrix = new char[n][m];
		visited = new boolean[n][m];

		int x = 0;
		int y = 0;
		for (int i = 0; i < n; i++) {
			String s = br.readLine();
			for (int j = 0; j < m; j++) {
				matrix[i][j] = s.charAt(j);
				if (matrix[i][j] == TARGET) {
					x = i;
					y = j;
				}
				if (matrix[i][j] == FIRE) {
					q.offer(i);
					q.offer(j);
					q.offer(0);
					visited[i][j] = true;
				}
			}
		}

		bfsFire(); // 불지르기

		q.offer(x);
		q.offer(y);
		q.offer(0);
		visited = new boolean[n][m];
		visited[x][y] = true;
		bfs(); // 지훈이 탈출 시키기

		if (answer == 999_999_999) {
			System.out.println("IMPOSSIBLE");
		} else {
			System.out.println(answer);
		}
	}

	private static void bfsFire() {
		while (!q.isEmpty()) {
			int x = q.poll();
			int y = q.poll();
			int time = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				if (nx >= n || nx < 0 || ny >= m || ny < 0) {
					continue;
				}
				if (matrix[nx][ny] == WALL || visited[nx][ny]) {
					continue;
				}

				visited[nx][ny] = true;
				matrix[nx][ny] = (char) (time + 1);

				q.offer(nx);
				q.offer(ny);
				q.offer(time + 1);
			}
		}
	}

	private static void bfs() {
		while (!q.isEmpty()) {
			int x = q.poll();
			int y = q.poll();
			int time = q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				if (nx >= n || nx < 0 || ny >= m || ny < 0) {
					if (time + 1 < answer) {
						answer = time + 1;
					}
					continue;
				}
				if (matrix[nx][ny] == WALL || visited[nx][ny]) {
					continue;
				}
				if ((int) (matrix[nx][ny]) <= time + 1) {
					continue;
				}
				q.offer(nx);
				q.offer(ny);
				q.offer(time + 1);
				visited[nx][ny] = true;
			}
		}
	}
}

