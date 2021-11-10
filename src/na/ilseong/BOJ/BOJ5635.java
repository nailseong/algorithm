package na.ilseong.BOJ;

import java.util.*;
import java.io.*;

public class BOJ5635 {

    static int N;
    static String[] names;
    static int[] values;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        names = new String[N];
        values = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            names[i] = st.nextToken();
            int day = Integer.parseInt(st.nextToken());
            int month = Integer.parseInt(st.nextToken());
            int year = Integer.parseInt(st.nextToken());
            values[i] += day;
            values[i] += month * 30;
            values[i] += (year - 1990) * 365;
        }

        int maxIndex = 0;
        int minIndex = 0;
        for (int i = 0; i < N; i++) {
            if (values[maxIndex] < values[i]) maxIndex = i;
            if (values[minIndex] > values[i]) minIndex = i;
        }


        System.out.println(names[maxIndex]);
        System.out.println(names[minIndex]);
    }

}
