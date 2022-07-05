package na.ilseong.BOJ.BOJ1000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ1476 {
    private static int[] arr = new int[3];
    private static int[] numbers = {1, 1, 1};

    public static void main(String[] args) throws Exception {
        input();

        int answer = 1;
        while(!isAnswer()) {
            answer++;
            for (int i = 0; i < 3; i++) {
                numbers[i] = numbers[i] + 1;
                if (i == 0 && numbers[i] > 15) {
                    numbers[i] = 1;
                }
                if (i == 1 && numbers[i] > 28) {
                    numbers[i] = 1;
                }
                if (i == 2 && numbers[i] > 19) {
                    numbers[i] = 1;
                }
            }
        }
        print(answer);
    }

    private static boolean isAnswer() {
        for (int i = 0; i < 3; i++) {
            if (arr[i] != numbers[i]) {
                return false;
            }
        }
        return true;
    }

    private static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 3; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }

    private static void print(int i) throws Exception {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(Integer.toString(i));
        bw.flush();
        bw.close();
    }
}
