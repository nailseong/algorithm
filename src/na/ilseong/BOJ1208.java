package na.ilseong;

import java.util.*;
import java.io.*;

public class BOJ1208 {

    static int N, S;
    static int[] series;

    static ArrayList<Integer> Left = new ArrayList<>();
    static ArrayList<Integer> Right = new ArrayList<>();

    static long ANSWER = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        series = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            series[i] = Integer.parseInt(st.nextToken());
        }

        initList(0, 0, N / 2, Left);
        initList(0, N / 2, N, Right);

        Collections.sort(Left);
        Collections.sort(Right);

        int leftIdx = 0;
        int rightIdx = Right.size() - 1;
        while (leftIdx < Left.size() && rightIdx >= 0) {

            int leftValue = Left.get(leftIdx);
            int rightValue = Right.get(rightIdx);

            if (leftValue + rightValue == S) {

                long leftCount = 0;
                while (leftIdx < Left.size() && Left.get(leftIdx) == leftValue) {
                    leftCount++;
                    leftIdx++;
                }

                long rightCount = 0;
                while (rightIdx >= 0 && Right.get(rightIdx) == rightValue) {
                    rightCount++;
                    rightIdx--;
                }

                ANSWER += leftCount * rightCount;

            } else if (leftValue + rightValue < S) {
                leftIdx++;
            } else {
                rightIdx--;
            }

        }

        System.out.println(S == 0 ? ANSWER - 1 : ANSWER);
    }

    private static void initList(int value, int idx, int lastIdx, ArrayList<Integer> list) {

        if (idx == lastIdx) {
            list.add(value);
            return;
        }

        initList(value, idx + 1, lastIdx, list);
        initList(value + series[idx], idx + 1, lastIdx, list);

    }

}
