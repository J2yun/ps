import java.io.*;
import java.util.*;

public class Main {
    static int N, H;
    static List<List<Integer>> tree = new ArrayList<>();
    static int[][] parents;
    static int[] depths;
    static boolean[] visit;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        H = (int) Math.ceil(Math.log(N) / Math.log(2)) + 1;
        parents = new int[N + 1][H];
        depths = new int[N + 1];
        visit = new boolean[N + 1];
        for (int i = 0; i <= N; i++) {
            tree.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            tree.get(a).add(b);
            tree.get(b).add(a);
        }

        makeDepths(1, 1);
        makeParents();

        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sb.append(getLca(a, b)).append("\n");
        }
        System.out.print(sb);
    }

    static void makeDepths(int now, int depth) {
        visit[now] = true;
        depths[now] = depth;
        for (int next : tree.get(now)) {
            if (!visit[next]) {
                parents[next][0] = now;
                makeDepths(next, depth + 1);
            }
        }
    }

    static void makeParents() {
        for (int j = 1; j < H - 1; j++) {
            for (int i = 1; i <= N; i++) {
                int tmp = parents[i][j - 1];
                parents[i][j] = parents[tmp][j - 1];
            }
        }
    }

    static int getLca(int a, int b) {
        // a를 더 깊게
        if (depths[a] < depths[b]) {
            int tmp = a;
            a = b;
            b = tmp;
        }

        // 높이 맞추기
        for (int i = H - 1; i >= 0; i--) {
            if (depths[a] - depths[b] >= (1 << i)) {
                a = parents[a][i];
            }
        }

        if (a == b) {
            return a;
        }

        for (int i = H - 1; i >= 0; i--) {
            if (parents[a][i] != parents[b][i]) {
                a = parents[a][i];
                b = parents[b][i];
            }
        }

        return parents[a][0];
    }
}
