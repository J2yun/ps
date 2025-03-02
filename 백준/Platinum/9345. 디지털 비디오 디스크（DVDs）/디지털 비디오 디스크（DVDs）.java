import java.io.*;
import java.util.*;

public class Main {
    static long[] minTree, maxTree, arr;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            arr = new long[N + 1];
            minTree = new long[4 * N + 1];
            maxTree = new long[4 * N + 1];

            for (int i = 0; i < N; i++) {
                arr[i] = i;
            }

            buildTree(1, 0, N - 1);

            // System.out.println(query(1, 0, 3, 0, N - 1));

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int cmd = Integer.parseInt(st.nextToken());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                if (cmd == 0) {
                    convert(a, b);
                } else {
                    long min = minQuery(1, a, b, 0, N - 1);
                    long max = maxQuery(1, a, b, 0, N - 1);
                    // sb.append(tmp).append(" ").append(cmp).append("\n");
                    if (min == a && max == b) {
                        sb.append("YES\n");
                    } else {
                        sb.append("NO\n");
                    }
                }
            }
        }
        System.out.print(sb);
    }

    static void buildTree(int node, int left, int right) {
        if (left == right) {
            minTree[node] = arr[left];
            maxTree[node] = arr[left];
            return;
        }

        int mid = (left + right) / 2;
        int leftNode = node * 2;
        int rightNode = node * 2 + 1;

        buildTree(leftNode, left, mid);
        buildTree(rightNode, mid + 1, right);

        minTree[node] = Math.min(minTree[leftNode], minTree[rightNode]);
        maxTree[node] = Math.max(maxTree[leftNode], maxTree[rightNode]);
    }

    static long minQuery(int node, int L, int R, int left, int right) {
        if (R < left || right < L) {
            return Integer.MAX_VALUE;
        }

        if (L <= left && right <= R) {
            return minTree[node];
        }

        int mid = (left + right) / 2;
        int leftNode = node * 2;
        int rightNode = node * 2 + 1;

        return Math.min(minQuery(leftNode, L, R, left, mid),
                minQuery(rightNode, L, R, mid + 1, right));
    }

    static long maxQuery(int node, int L, int R, int left, int right) {
        if (R < left || right < L) {
            return 0;
        }

        if (L <= left && right <= R) {
            return maxTree[node];
        }

        int mid = (left + right) / 2;
        int leftNode = node * 2;
        int rightNode = node * 2 + 1;

        return Math.max(maxQuery(leftNode, L, R, left, mid),
                maxQuery(rightNode, L, R, mid + 1, right));
    }

    static void convert(int a, int b) {
        long tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;

        updateNode(1, a, 0, N - 1);
        updateNode(1, b, 0, N - 1);
    }

    static void updateNode(int node, int idx, int left, int right) {
        if (left == right) {
            minTree[node] = arr[idx];
            maxTree[node] = arr[idx];
            return;
        }

        int mid = (left + right) / 2;
        int leftNode = node * 2;
        int rightNode = node * 2 + 1;

        if (idx <= mid) {
            updateNode(leftNode, idx, left, mid);
        } else {
            updateNode(rightNode, idx, mid + 1, right);
        }

        minTree[node] = Math.min(minTree[leftNode], minTree[rightNode]);
        maxTree[node] = Math.max(maxTree[leftNode], maxTree[rightNode]);
    }
}
