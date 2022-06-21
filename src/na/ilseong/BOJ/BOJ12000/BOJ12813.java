package na.ilseong.BOJ.BOJ12000;

import java.util.*;
import java.io.*;

public class BOJ12813 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static String A, B;
    static int length = 100_000;

    static StringBuilder ANSWER = new StringBuilder();

    public static void main(String[] args) throws IOException {
        A = br.readLine();
        B = br.readLine();

        // A & B
        for (int i = 0; i < length; i++) {
            if (A.charAt(i) == '1' && B.charAt(i) == '1') {
                ANSWER.append('1');
            } else {
                ANSWER.append('0');
            }
        }
        ANSWER.append("\n");

        // A | B
        for (int i = 0; i < length; i++) {
            if (A.charAt(i) == '1' || B.charAt(i) == '1') {
                ANSWER.append('1');
            } else {
                ANSWER.append('0');
            }
        }
        ANSWER.append("\n");

        // A ^ B
        for (int i = 0; i < length; i++) {
            if (A.charAt(i) != B.charAt(i)) {
                ANSWER.append('1');
            } else {
                ANSWER.append('0');
            }
        }
        ANSWER.append("\n");

        // ~A
        for (int i = 0; i < length; i++) {
            if (A.charAt(i) == '1') {
                ANSWER.append('0');
            } else {
                ANSWER.append('1');
            }
        }
        ANSWER.append("\n");

        // ~B
        for (int i = 0; i < length; i++) {
            if (B.charAt(i) == '1') {
                ANSWER.append('0');
            } else {
                ANSWER.append('1');
            }
        }
        ANSWER.append("\n");

        System.out.println(ANSWER);
    }

}
