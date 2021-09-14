package na.ilseong;

import java.util.*;
import java.io.*;

// println -> StringBuilder
// 한방에 출력
public class BOJ11723 {

    static int M;
    static boolean[] series = new boolean[21];

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());

            String command = st.nextToken();

            int element;
            switch (command) {
                case "add":
                    element = Integer.parseInt(st.nextToken());
                    series[element] = true;
                    break;

                case "remove":
                    element = Integer.parseInt(st.nextToken());
                    series[element] = false;
                    break;

                case "check":
                    element = Integer.parseInt(st.nextToken());
                    sb.append(series[element] ? "1\n" : "0\n");
                    break;

                case "toggle":
                    element = Integer.parseInt(st.nextToken());
                    series[element] = !series[element];
                    break;

                case "all":
                    Arrays.fill(series, true);
                    break;

                case "empty":
                    Arrays.fill(series, false);
                    break;

            }
        }

        System.out.println(sb);
    }

}
