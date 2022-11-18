package na.ilseong.BOJ.BOJ2000;

import java.util.*;
import java.io.*;

class BOJ2240 {

	static int[][] dp = new int[31][1001];
	static int[] arr = new int[1001];
	static int t;
	static int w;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		t = Integer.parseInt(st.nextToken()); w = Integer.parseInt(st.nextToken());
		for (int i = 1; i <= t; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		 	if (arr[i] % 2 == 1) dp[0][i] = dp[0][i - 1] + 1;
			else dp[0][i] = dp[0][i - 1];
		}


		for (int i = 1; i <= w; i++) {
			for (int j = 1; j <= t; j++) {
				int tmp = 0;
				if (dp[i][j - 1] > dp[i - 1][j - 1]) tmp = dp[i][j - 1];
				else tmp = dp[i - 1][j - 1];

				if (i % 2 == 0 && arr[j] == 1) tmp++;
				if (i % 2 == 1 && arr[j] == 2) tmp++;
				dp[i][j] = tmp;
			}
		}

		int answer = -1;
		for (int i = 0; i <= w; i++) {
			if (answer < dp[i][t]) answer = dp[i][t];
		}
		System.out.println(answer);
	}
}

