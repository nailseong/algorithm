package na.ilseong.BOJ.BOJ13000;

import java.io.*;
import java.util.*;

public class BOJ13335 {

    static int n;
    static int w;
    static int l;
    static Queue<Integer> trucks = new LinkedList<>();

    static int count = 0;

    public static void main(String[] args) throws Exception {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            trucks.offer(Integer.parseInt(st.nextToken()));
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < w; i++) {
            q.offer(0);
        }

        int currentWeight = 0;
        int currentTruckCount = 0;
        while (!trucks.isEmpty() || !q.isEmpty()) {
            count++;

            if (!q.isEmpty()) {
                final Integer exitTruck = q.poll();
                currentWeight -= exitTruck;
                if (exitTruck != 0) currentTruckCount--;
            }

            if (!trucks.isEmpty()) {
                final Integer nextTruck = trucks.peek();
                if (currentWeight + nextTruck > l || currentTruckCount == l) {
                    q.offer(0);
                    continue;
                }

                final Integer truck = trucks.poll();
                q.offer(truck);
                currentWeight += truck;
                currentTruckCount++;
            }
        }

        System.out.println(count);
    }
}
