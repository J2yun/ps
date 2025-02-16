import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static long[] arr, tree, lazy;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken());;
        arr = new long[N + 1];
        tree = new long[4 * N + 1];
        lazy = new long[4 * N + 1];

        for (int i = 1; i <= N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }
        buildTree(1, 1, N);

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            if (cmd == 1) {
                int left = Integer.parseInt(st.nextToken());
                int right = Integer.parseInt(st.nextToken());
                long diff = Long.parseLong(st.nextToken());
                updateRange(1, left, right, 1, N, diff);
            } else {
                int left = Integer.parseInt(st.nextToken());
                int right = Integer.parseInt(st.nextToken());
                long tmp = query(1, left, right, 1, N);
                sb.append(tmp).append("\n");
            }
        }
        System.out.print(sb);
    }

    static void buildTree(int node, int left, int right) {
        if (left == right) {
            tree[node] = arr[left];
            return;
        }

        int mid = (left + right) / 2;
        int leftNode = node * 2;
        int rightNode = node * 2 + 1;

        buildTree(leftNode, left, mid);
        buildTree(rightNode, mid + 1, right);

        tree[node] = tree[leftNode] + tree[rightNode];
    }

    static void lazy_propagation(int node, int left, int right) {
        if (lazy[node] != 0) {
            tree[node] += (right - left + 1) * lazy[node];
            if (left != right) {
                lazy[node * 2] += lazy[node];
                lazy[node * 2 + 1] += lazy[node];
            }
            lazy[node] = 0;
        }
    }

    static void updateRange(int node, int L, int R, int left, int right, long diff) {
        lazy_propagation(node, left, right);
        if (R < left || right < L) {
            return;
        }

        int mid = (left + right) / 2;
        int leftNode = node * 2;
        int rightNode = node * 2 + 1;

        if (L <= left && right <= R) {
            tree[node] += (right - left + 1) * diff;
            if (left != right) {
                lazy[leftNode] += diff;
                lazy[rightNode] += diff;
            }
            return;
        }

        updateRange(leftNode, L, R, left, mid, diff);
        updateRange(rightNode, L, R, mid + 1, right, diff);

        tree[node] = tree[leftNode] + tree[rightNode];
    }

    static long query(int node, int L, int R, int left, int right) {
        lazy_propagation(node, left, right);
        if (R < left || right < L) {
            return 0;
        }

        if (L <= left && right <= R) {
            return tree[node];
        }

        int mid = (left + right) >> 1;
        return query(node * 2, L, R, left, mid) + query(node * 2 + 1, L, R, mid + 1, right);
    }
}
