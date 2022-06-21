package na.ilseong.BOJ.BOJ9000;

import java.util.*;
import java.io.*;

public class BOJ9935 {

    static String series;
    static String bomb;

    static String ANSWER = "FRULA";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        series = br.readLine();
        bomb = br.readLine();

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < series.length(); i++) {

            stack.push(series.charAt(i));

            if (stack.size() >= bomb.length()) {
                if (isMatch(stack)) {
                    for (int j = 0; j < bomb.length(); j++) {
                        stack.pop();
                    }
                }
            }

        }

        String result = toString(stack);
        if (!result.isEmpty()) {
            ANSWER = result;
        }

        System.out.println(ANSWER);
    }

    // 폭탄 체크
    private static boolean isMatch(Stack<Character> stack) {
        for (int i = 0; i < bomb.length(); i++) {
            if (stack.get(stack.size() - bomb.length() + i) != bomb.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    private static String toString(Stack<Character> stack) {
        StringBuilder result = new StringBuilder();

        for (Character c : stack) {
            result.append(c);
        }

        return result.toString();
    }

}
