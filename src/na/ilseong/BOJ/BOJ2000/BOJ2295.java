package na.ilseong.BOJ.BOJ2000;

import java.util.*;
import java.io.*;

public class BOJ2295 {

	static int[] arr = new int[1001];
	static int[] sum = new int[1_002_001];
	static int n;
	static int count = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(arr, 0, n);

		for (int i = 0; i < n; i++) {
			for (int j = i; j < n; j++) {
				sum[count++] = arr[i] + arr[j];
			}
		}
		Arrays.sort(sum, 0, count);

		for (int k = n - 1; k >= 0; k--) {
			for (int z = k; z >= 0; z--) {
				if (bs(arr[k] - arr[z])) {
					System.out.println(arr[k]);
					return;
				}
			}
		}
	}

	private static boolean bs(int target) {
		int st = 0;
		int en = count - 1;
		int mid;

		while (st <= en) {
			mid = (en + st) / 2;
			if (sum[mid] == target) return true;
			if (sum[mid] > target) en = mid - 1;
			if (sum[mid] < target) st = mid + 1;
		}
		return false;
	}
}

