import java.io.*;
import java.util.*;

public class Main {
    static int[][] table;
    static int[] depth;
    static List<Integer>[] tree;
    static int N, height;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());
            height = (int) Math.ceil(Math.log(N) / Math.log(2)) + 1;
            table = new int[height][N + 1];
            depth = new int[N + 1];
            tree = new List[N + 1];
            for (int i = 0; i <= N; i++) {
                tree[i] = new ArrayList<>();
            }

            int a, b;
            for (int i = 0; i < N - 1; i++) {
                st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                table[0][b] = a;
                tree[a].add(b);
            }

            makeTable();
            int root = -1;
            for (int i = 1; i <= N; i++) {
                if (table[0][i] == 0) {
                    root = i;
                    break;
                }
            }
            makeDepth(root, 1);

            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            sb.append(lca(a, b)).append("\n");
        }

        System.out.print(sb);
    }

    static void makeTable() {
        for (int h = 1; h < height; h++) {
            for (int node = 1; node <= N; node++) {
                int tmp = table[h - 1][node];
                table[h][node] = table[h - 1][tmp];
            }
        }
    }

    static void makeDepth(int root, int d) {
        depth[root] = d;
        for (int next : tree[root]) {
            makeDepth(next, d + 1);
        }
    }

    static int lca(int a, int b) {
        if (depth[a] < depth[b]) {
            int tmp = a;
            a = b;
            b = tmp;
        }

        for (int i = height - 1; i >= 0; i--) {
            if (depth[a] - depth[b] >= (1 << i)) {
                a = table[i][a];
            }
        }

        if (a == b) {
            return a;
        }

        for (int i = height - 1; i >= 0; i--) {
            if (table[i][a] != table[i][b]) {
                a = table[i][a];
                b = table[i][b];
            }
        }

        return table[0][a];
    }
}
