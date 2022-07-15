package na.ilseong.BOJ.BOJ2000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ2609 {

    private static int a;
    private static int b;
    private static BufferedWriter bw;

    public static void main(String[] args) throws Exception {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        int gcd = solve(a, b);
        int lcm = a * b / gcd;

        bw.write(gcd + "\n" + lcm);

        bw.flush();
        bw.close();
    }

    private static int solve(int i, int j) {
        if (i == 0 || j == 0) {
            return i > j ? i : j;
        }
        return i > j ? solve(j, i % j) : solve(i, j % i);
    }
}
