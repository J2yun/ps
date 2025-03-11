import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        ArrayDeque<Integer> que = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            que.add(i);
        }

        for (int i = 1; i < N; i++) {
            que.removeFirst();
            que.add(que.removeFirst());
        }

        System.out.println(que.peek());
    }
}

