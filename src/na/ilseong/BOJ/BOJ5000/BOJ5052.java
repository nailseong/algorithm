package na.ilseong.BOJ.BOJ5000;

import java.util.*;
import java.io.*;

public class BOJ5052 {

    static int T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());

            String[] series = new String[N];

            for (int j = 0; j < N; j++) {
                series[j] = br.readLine();
            }

            Arrays.sort(series);

            String answer = "YES";
            for (int j = 0; j < N - 1; j++) {

                String current = series[j];
                String next = series[j + 1];

                if (next.startsWith(current)) {
                    answer = "NO";
                    break;
                }

            }

            System.out.println(answer);
        }

    }

}
