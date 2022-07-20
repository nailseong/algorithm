package na.ilseong.BOJ.BOJ1000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1158 {
    static BufferedWriter bw;

    public static void main(String[] args) throws Exception {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Queue<String> q = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            q.offer(String.valueOf(i));
        }

        Queue<String> ans = new LinkedList<>();
        while(!q.isEmpty()) {
            int count = 0;
            while (count < k) {
                String s = q.poll();

                count++;

                if (count == k) {
                    ans.offer(s);
                } else {
                    q.offer(s);
                }
            }
        }

        bw.write("<");
        bw.write(String.join(", ", ans));
        bw.write(">");

        bw.flush();
        bw.close();
    }
}
