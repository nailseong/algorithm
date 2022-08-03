package na.ilseong.BOJ.BOJ16000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ16922 {

    static Set<Integer> set = new HashSet<>();
    static int n;
    static int[] a = {1, 5, 10, 50};

    public static void main(String[] args) throws Exception {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        method(0, 0, 0);

        bw.write(set.size() + "");
        bw.flush();
        bw.close();
    }

    private static void method(int sum, int count, int preIndex) {
        if (count == n) {
            set.add(sum);
            return;
        }
        for (int i = preIndex; i < 4; i++) {
            method(sum + a[i], count + 1, i);
        }
    }
}
