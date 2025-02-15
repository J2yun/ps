import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static long[] arr, lazy, seg;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        arr = new long[N + 1];
        lazy = new long[4 * N + 1];
        seg = new long[4 * N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        buildTree(1, 1, N);

        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            if (cmd == 1) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int diff = Integer.parseInt(st.nextToken());
                updateRange(1, a, b, 1, N, diff);
            } else {
                int x = Integer.parseInt(st.nextToken());
                sb.append(get(x)).append("\n");
            }
        }
        System.out.print(sb);
    }

    static void buildTree(int node, int left, int right) {
        if (left == right) {
            seg[node] = arr[left];
            return;
        }
        int mid = (left + right) / 2;
        int leftNode = node * 2;
        int rightNode = node * 2 + 1;

        buildTree(leftNode, left, mid);
        buildTree(rightNode, mid + 1, right);
    }

    static void lazyUpdate(int node, int left, int right) {
        if (lazy[node] != 0) {
            seg[node] += lazy[node];
            if (left != right) {
                lazy[2 * node] += lazy[node];
                lazy[2 * node + 1] += lazy[node];
            }
            lazy[node] = 0;
        }
    }

    static void updateRange(int node, int L, int R, int left, int right, int diff) {
        lazyUpdate(node, left, right);
        if (right < L || R < left) {
            return;
        }

        int mid = (left + right) / 2;
        int leftNode = node * 2;
        int rightNode = node * 2 + 1;

        if (L <= left && right <= R) {
            seg[node] += diff;
            if (left != right) {
                lazy[leftNode] += diff;
                lazy[rightNode] += diff;
            }
            return;
        }

        updateRange(leftNode, L, R, left, mid, diff);
        updateRange(rightNode, L, R, mid + 1, right, diff);
    }

    static long get(int idx) {
        return get(1, 1, N, idx);
    }

    static long get(int node, int left, int right, int idx) {
        lazyUpdate(node, left, right);
        if (left == right) {
            return seg[node];
        }

        int mid = (left + right) / 2;
        int leftNode = node * 2;
        int rightNode = node * 2 + 1;

        if (idx <= mid) {
            return get(leftNode, left, mid, idx);
        } else {
            return get(rightNode, mid + 1, right, idx);
        }
    }
}
