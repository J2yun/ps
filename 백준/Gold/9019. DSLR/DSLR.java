import java.io.*;
import java.util.*;

public class Main {
    private static final int MOD = 10_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            String min = bfs(a, b);
            sb.append(min).append("\n");
        }

        System.out.print(sb);
    }

    static String bfs(int s, int target) {
        Queue<Element> que = new LinkedList<>();
        que.add(new Element(s, ""));
        String ret = "";

        boolean[] visit = new boolean[MOD];
        visit[s] = true;

        while (!que.isEmpty()) {
            Element now = que.remove();
            if (now.val == target) {
                ret = now.cmd;
                break;
            }
            // D, S
            if (!visit[now.D()]) {
                que.add(new Element(now.D(), now.cmd + "D"));
                visit[now.D()] = true;
            }
            if (!visit[now.S()]) {
                que.add(new Element(now.S(), now.cmd + "S"));
                visit[now.S()] = true;
            }
            if (!visit[now.L()]) {
                que.add(new Element(now.L(), now.cmd + "L"));
                visit[now.L()] = true;
            }
            if (!visit[now.R()]) {
                que.add(new Element(now.R(), now.cmd + "R"));
                visit[now.R()] = true;
            }

        }

        return ret;
    }

    static class Element {
        int val;
        String cmd;

        public Element(int val, String cmd) {
            this.val = val;
            this.cmd = cmd;
        }

        int D() {
            return val * 2 % MOD;
        }

        int S() {
            return val == 0 ? 9999 : val - 1;
        }

        int L() {
            return val % 1000 * 10 + val / 1000;
        }

        int R() {
            return val % 10 * 1000 + val / 10;
        }
    }
}
