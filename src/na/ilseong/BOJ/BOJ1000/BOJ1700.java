package na.ilseong.BOJ.BOJ1000;

import java.util.*;
import java.io.*;

public class BOJ1700 {

    static int N, K, count, answer;
    static int[] series;
    static boolean[] isInUse;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        series = new int[K];
        isInUse = new boolean[K + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            series[i] = Integer.parseInt(st.nextToken());
        }

        count = 0;
        answer = 0;
        for (int i = 0; i < K; i++) {

            // 이미 플러그에 꽂혀있음
            if (isInUse[series[i]]) continue;

            // 빈 자리가 있음
            if (count < N) {
                isInUse[series[i]] = true;
                count++;
                continue;
            }

            // 플러그에서 뽑기
            ArrayList<Integer> list = new ArrayList<>();
            // 나중에도 사용되는 플러그 개수 카운트
            for (int j = i; j < K; j++) {
                int tmp = series[j];

                if (!isInUse[tmp]) continue;

                if (list.contains(tmp)) continue;

                list.add(tmp);

            }

            if (list.size() < N) { // 더 이상 중복으로 사용하지 않는 플러그 제거
                for (int j = 1; j <= K; j++) {
                    if (isInUse[j] && !list.contains(j)) {
                        isInUse[j] = false;
                        break;
                    }
                }
            } else {
                // 모든 플러그가 중복으로 사용됨 -> 가장 나중에 다시 사용되는 플러그 제거
                // 즉, list에 마지막으로 추가된 플러그 제거
                isInUse[list.get(list.size() - 1)] = false;
            }

            answer++;
            isInUse[series[i]] = true;
        }

        System.out.println(answer);
    }

}
