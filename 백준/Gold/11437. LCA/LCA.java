import java.io.*;
import java.util.*;

public class Main {
    static List<List<Integer>> tree;
    static int[][] parents;
    static int[] depths;
    static boolean[] visit;
    static int N, H;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());

        // 값 초기화
        tree = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            tree.add(new ArrayList<>());
        }
        H = (int) Math.ceil(Math.log(N) / Math.log(2)) + 1;
        parents = new int[N + 1][H];
        depths = new int[N + 1];
        visit = new boolean[N + 1];

        for (int i = 0; i < N - 1; i++) {
            String[] tmp = br.readLine().split(" ");
            int a = Integer.parseInt(tmp[0]);
            int b = Integer.parseInt(tmp[1]);

            tree.get(a).add(b);
            tree.get(b).add(a);
        }

        dfs(1, 0);
        makeParent();

        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            String[] tmp = br.readLine().split(" ");
            int a = Integer.parseInt(tmp[0]);
            int b = Integer.parseInt(tmp[1]);

            sb.append(getLca(a, b)).append("\n");
        }

        System.out.print(sb);
    }

    // root는 1번
    // dfs를 이용해서 parents 초기, depth 채우기
    static void dfs(int now, int depth) {
        visit[now] = true;
        depths[now] = depth;
        for (int i : tree.get(now)) {
            if (!visit[i]) {
                parents[i][0] = now;
                dfs(i, depth + 1);
            }
        }
    }

    // 2^n 번쨰 조상을 가지는 희소배열(parents) 만들기
    static void makeParent() {
        for (int i = 1; i < H; i++) {
            for (int node = 1; node <= N; node++) {
                int tmp = parents[node][i - 1];
                parents[node][i] = parents[tmp][i - 1];
            }
        }
    }

    static int getLca(int a, int b) {
        // 0. a가 더 깊은 노드로
        if (depths[a] < depths[b]) {
            int tmp = a;
            a = b;
            b = tmp;
        }

        // 1. a와 b의 높이를 맞춰 주어야 함
        for (int i = H - 1; i >= 0; i--) {
            if (depths[a] - depths[b] >= (1 << i)) {
                a = parents[a][i];
            }
        }

        // 2. 맞춰준 후, 같으면 공통 조상
        if (a == b) {
            return a;
        }

        // 3. 거슬러 올라가면서 공통 조상 찾기
        for (int i = H - 1; i >= 0; i--) {
            if (parents[a][i] != parents[b][i]) {
                a = parents[a][i];
                b = parents[b][i];
            }
        }

        // 4. 해당 로직이 수행되고 나면, 바로 위가 공통 조상일때까지 a, b가 거슬러 올라감
        return parents[a][0];
    }
}
