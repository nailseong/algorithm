package na.ilseong.BOJ.BOJ14000;

import java.util.*;
import java.io.*;

public class BOJ14891 {

    static boolean S = true;
    static int RIGHT = 1;
    static int LEFT = -1;

    static List<Deque<Boolean>> gears = new ArrayList<>();
    static Queue<Integer> q = new LinkedList<>();
    static int k;

    public static void main(String[] args) throws Exception {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 4; i++) {
            final String tmp = br.readLine();
            final Deque<Boolean> dq = new LinkedList<>();
            for (int j = 0; j < 8; j++) {
                dq.offerLast(tmp.charAt(j) == '1');
            }
            gears.add(dq);
        }

        k = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            q.offer(Integer.parseInt(st.nextToken()) - 1);
            q.offer(Integer.parseInt(st.nextToken()));
        }

        while (k-- > 0) {
            final Integer gear = q.poll();
            final Integer dir = q.poll();

            Queue<Integer> turnQ = new LinkedList<>();
            turnQ.offer(gear);
            turnQ.offer(dir);

            int currentDir = dir == RIGHT ? LEFT : RIGHT;
            for (int i = gear + 1; i < 4; i++) {
                if (!needTurn(i, i - 1)) break;
                turnQ.offer(i);
                turnQ.offer(currentDir);
                currentDir = currentDir == RIGHT ? LEFT : RIGHT;
            }

            currentDir = dir == RIGHT ? LEFT : RIGHT;
            for (int i = gear - 1; i >= 0; i--) {
                if (!needTurn(i, i + 1)) break;
                turnQ.offer(i);
                turnQ.offer(currentDir);
                currentDir = currentDir == RIGHT ? LEFT : RIGHT;
            }

            while (!turnQ.isEmpty()) {
                final Integer gearIdx = turnQ.poll();
                final Integer d = turnQ.poll();
                turn(gearIdx, d);
            }
        }

        int count = 0;
        int answer = 0;
        for (int i = 0; i < 4; i++) {
            final Deque<Boolean> gear = gears.get(i);
            if (((LinkedList<?>) gear).get(0).equals(S)) {
                if (count == 0) {
                    answer += 1;
                } else if (count == 1) {
                    answer += 2;
                } else if (count == 2) {
                    answer += 4;
                } else {
                    answer += 8;
                }
            }
            count++;
        }

        System.out.println(answer);
    }

    private static boolean needTurn(final int targetGearIdx, final int sourceGearIdx) {
        final Deque<Boolean> target = gears.get(targetGearIdx);
        final Deque<Boolean> source = gears.get(sourceGearIdx);

        if (targetGearIdx < sourceGearIdx) return ((LinkedList<?>) target).get(2) != ((LinkedList<?>) source).get(6);
        else return ((LinkedList<?>) source).get(2) != ((LinkedList<?>) target).get(6);
    }

    private static void turn(final int gearIdx, final int dir) {
        final Deque<Boolean> gear = gears.get(gearIdx);
        if (dir == RIGHT) {
            final Boolean b = gear.pollLast();
            gear.addFirst(b);
        } else {
            final Boolean b = gear.pollFirst();
            gear.addLast(b);
        }
    }
}
