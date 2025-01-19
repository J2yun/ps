import java.io.*;
import java.util.*;

public class Main {
    static ArrayDeque<Character> alp = new ArrayDeque<>();
    static ArrayDeque<Character> sig = new ArrayDeque<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String mid = br.readLine();
        for (int i = 0; i < mid.length(); i++) {
            char c = mid.charAt(i);
            if ('A' <= c && c <= 'Z') {
                sb.append(c);
            } else if (c == ')') {
                flush();
            } else if (c == '(') {
                sig.add(c);
            } else {
                while (!sig.isEmpty() && priority(sig.peekLast()) >= priority(c)) {
                    sb.append(sig.removeLast());
                }
                sig.add(c);
            }
            // System.out.println(alp);
            // System.out.println(sig);
            // System.out.println();
        }
        flush();
        for (char c : alp) {
            sb.append(c);
        }
        System.out.println(sb);
    }

    static int priority(char c) {
        if (c == '/' || c == '*') {
            return 2;
        } else if (c == '+' || c == '-') {
            return 1;
        }
        return 0;
    }

    static void flush() {
        while (!sig.isEmpty()) {
            char c = sig.removeLast();
            if (c == '(') {
                break;
            } else if (c != ')') {
                sb.append(c);
            }
        }
    }
}
