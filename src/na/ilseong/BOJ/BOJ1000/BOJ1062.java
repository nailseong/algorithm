package na.ilseong.BOJ.BOJ1000;

import java.util.*;
import java.io.*;

public class BOJ1062 {

    static int N, K;
    static String[] series;
    static int[] mark = new int[26];

    static int MAX = 5;
    static int ANSWER = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // a, c, i, n, t
        if (K < 5) {
            System.out.println(0);
            return;
        }

        if (K == 26) {
            System.out.println(N);
            return;
        }

        mark['a' - 'a'] = 1;
        mark['n' - 'a'] = 1;
        mark['t' - 'a'] = 1;
        mark['i' - 'a'] = 1;
        mark['c' - 'a'] = 1;

        series = new String[N];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            series[i] = input.substring(4, input.length() - 4);
            for (int j = 0; j < series[i].length(); j++) {
                if (mark[series[i].charAt(j) - 'a'] == 0) {
                    MAX++;
                    mark[series[i].charAt(j) - 'a'] = -1;
                }
            }
        }

        solve(0, 5);

        System.out.println(ANSWER);
    }

    private static void solve(int index, int num) {

        if (num == K || num == MAX) {
            calculate();
            return;
        }

        if (index == 26) return;

        if (mark[index] == -1) {
            mark[index] = 1;
            solve(index + 1, num + 1);
            mark[index] = -1;
        }

        solve(index + 1, num);

    }

    private static void calculate() {
        int count = 0;

        for (String s : series) {

            boolean flag = true;
            for (int i = 0; i < s.length(); i++) {
                if (mark[s.charAt(i) - 'a'] == -1) {
                    flag = false;
                    break;
                }
            }

            if (flag) count++;

        }

        ANSWER = Math.max(ANSWER, count);
    }

    private static void print() {
        for (int i = 0; i < 26; i++) {
            if (mark[i] == -1) {
                System.out.print(
                        (char) ('a' + i) + ": " + mark[i] + "   "
                );
            } else if (mark[i] == 1){
                System.out.print(
                        (char) ('a' + i) + ":  " + mark[i] + "   "
                );
            }
        }
        System.out.println("\n");
    }

}
