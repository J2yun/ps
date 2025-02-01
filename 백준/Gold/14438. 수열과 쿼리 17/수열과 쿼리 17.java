import java.io.*;
import java.util.*;

public class Main {
    static int[] arr, tree;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        tree = new int[4 * N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        buildTree(1, 1, N);
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (cmd == 1) {
                updateTree(a, b);
            } else {
                sb.append(getMin(a, b)).append("\n");
            }
        }
        System.out.print(sb);
        // System.out.println();
        // System.out.println(getMin(4, 4));
    }

    static void buildTree(int node, int left, int right) {
        if (left == right) {
            tree[node] = arr[left];
            return;
        }

        int leftNode = node * 2;
        int rightNode = node * 2 + 1;
        int mid = (left + right) / 2;

        buildTree(leftNode, left, mid);
        buildTree(rightNode, mid + 1, right);

        tree[node] = Math.min(tree[leftNode], tree[rightNode]);
    }

    static int getMin(int a, int b) {
        return getMin(1, a, b, 1, N);
    }

    static int getMin(int node, int left, int right, int L, int R) {
        if (right < L || R < left) {
            return Integer.MAX_VALUE;
        }

        if (left <= L && R <= right) {
            // System.out.printf("\n%d,%d : [%d] ", left, right, tree[node]);
            return tree[node];
        }

        int leftNode = node * 2;
        int rightNode = node * 2 + 1;
        int mid = (L + R) / 2;

        return Math.min(getMin(leftNode, left, right, L, mid),
                getMin(rightNode, left, right, mid + 1, R));
    }

    static void updateTree(int idx, int val) {
        updateTree(1, 1, N, idx, val);
    }

    static void updateTree(int node, int left, int right, int idx, int val) {
        if (left == right) {
            tree[node] = val;
            return;
        }

        int leftNode = node * 2;
        int rightNode = node * 2 + 1;
        int mid = (left + right) / 2;

        if (idx <= mid) {
            updateTree(leftNode, left, mid, idx, val);
        } else {
            updateTree(rightNode, mid + 1, right, idx, val);
        }

        tree[node] = Math.min(tree[leftNode], tree[rightNode]);
    }
}
