import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        LinkedList<Pair> stack = new LinkedList<>();
        stack.add(new Pair(Long.MAX_VALUE, 0));
        stack.add(new Pair(Long.parseLong(br.readLine()), 1));

        long cnt = 0;
        for (int i = 1; i < N; i++) {
            long n = Long.parseLong(br.readLine());
            Pair p = new Pair(n, 1);

            if (stack.peekLast().h <= n) {
                while (true) {
                    cnt += stack.peekLast().cnt;
                    Pair rp = stack.removeLast();
                    if (rp.h == n) {
                        p.cnt += rp.cnt;
                    }

                    if (stack.peekLast().h > n) {
                        if (stack.peekLast().h != Long.MAX_VALUE) {
                            cnt += 1;
                        }
                        break;
                    }
                }
                stack.add(p);
            } else {
                cnt += 1;
                stack.add(p);
            }
        }
        System.out.println(cnt);
    }

    static class Pair {
        long h;
        long cnt;

        public Pair(long h, long cnt) {
            this.h = h;
            this.cnt = cnt;
        }
    }
}
