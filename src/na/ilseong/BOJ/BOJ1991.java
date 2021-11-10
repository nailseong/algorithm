package na.ilseong.BOJ;

import java.io.*;
import java.util.*;

public class BOJ1991 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N;
    static Node[] tree;

    static StringBuilder ANSWER = new StringBuilder();

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        tree = new Node[N];
        for (int i = 0; i < N; i++) {
            tree[i] = new Node((char) ('A' + i));
        }

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            char root = st.nextToken().charAt(0);
            char left = st.nextToken().charAt(0);
            char right = st.nextToken().charAt(0);

            if (left != '.') {
                tree[root - 'A'].left = tree[left - 'A'];
            }
            if (right != '.') {
                tree[root - 'A'].right = tree[right - 'A'];
            }
        }

        preorder(0);
        ANSWER.append("\n");

        inorder(0);
        ANSWER.append("\n");

        postorder(0);

        System.out.println(ANSWER);
    }

    private static void preorder(int idx) {
        ANSWER.append(tree[idx].value);
        if (tree[idx].left != null) {
            preorder(tree[idx].left.value - 'A');
        }
        if (tree[idx].right != null) {
            preorder(tree[idx].right.value - 'A');
        }
    }

    private static void inorder(int idx) {
        if (tree[idx].left != null) {
            inorder(tree[idx].left.value - 'A');
        }
        ANSWER.append(tree[idx].value);
        if (tree[idx].right != null) {
            inorder(tree[idx].right.value - 'A');
        }
    }

    private static void postorder(int idx) {
        if (tree[idx].left != null) {
            postorder(tree[idx].left.value - 'A');
        }
        if (tree[idx].right != null) {
            postorder(tree[idx].right.value - 'A');
        }
        ANSWER.append(tree[idx].value);
    }

    private static class Node {

        char value;
        Node left;
        Node right;

        public Node(char value) {
            this.value = value;
        }

    }
}
