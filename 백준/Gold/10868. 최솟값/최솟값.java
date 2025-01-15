import java.io.*;

public class Main {
    static int[] arr, tree;
    static int MAX = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String tmp[] = br.readLine().split(" ");
        int N = Integer.parseInt(tmp[0]);
        int M = Integer.parseInt(tmp[1]);

        arr = new int[N + 1];
        tree = new int[N * 4];

        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        buildTree(1, 1, N);

        for (int i = 0; i < M; i++) {
            tmp = br.readLine().split(" ");
            int a = Integer.parseInt(tmp[0]);
            int b = Integer.parseInt(tmp[1]);
            sb.append(getMin(1, 1, N, a, b)).append("\n");
        }

        System.out.print(sb);
    }

    public static void buildTree(int node, int L, int R) {
        if (L == R) {
            tree[node] = arr[L];
            return;
        }

        int left = node * 2;
        int right = node * 2 + 1;
        int mid = (L + R) / 2;

        buildTree(left, L, mid);
        buildTree(right, mid + 1, R);
        tree[node] = Math.min(tree[left], tree[right]);
    }

    public static int getMin(int node, int L, int R, int left, int right) {
        if (right < L || left > R) {
            return MAX;
        }

        if (left <= L && R <= right) {
            return tree[node];
        }

        int mid = (L + R) / 2;

        return Math.min(getMin(node * 2, L, mid, left, right),
                getMin(node * 2 + 1, mid + 1, R, left, right));
    }
}
