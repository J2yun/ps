import java.io.*;
import java.util.*;

public class Main {
    static int[] arr;
    static int[][] segTree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        segTree = new int[4 * N + 1][2];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        buildTree(1, 1, N);

        int cnt = Integer.parseInt(br.readLine());
        for (int i = 0; i < cnt; i++) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            if (cmd == 2) {
                sb.append(getMinIdx()).append("\n");
            } else {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                arr[a] = b;
                updateTree(a, 1, 1, N);
            }

        }
        System.out.print(sb);
    }

    static int[] cmpNode(int[] a, int[] b) {
        if (a[1] < b[1]) {
            return a;
        } else if (a[1] > b[1]) {
            return b;
        } else {
            if (a[0] < b[0]) {
                return a;
            } else {
                return b;
            }
        }
    }

    static void buildTree(int node, int left, int right) {
        if (left == right) {
            segTree[node] = new int[] {left, arr[left]}; // idx, value
            return;
        }

        int mid = (left + right) / 2;
        int leftNode = node * 2;
        int rightNode = node * 2 + 1;

        buildTree(leftNode, left, mid);
        buildTree(rightNode, mid + 1, right);

        segTree[node] = cmpNode(segTree[leftNode], segTree[rightNode]);
    }

    static int getMinIdx() {
        return segTree[1][0];
    }

    static void updateTree(int idx, int node, int left, int right) {
        if (left == right) {
            segTree[node] = new int[] {idx, arr[idx]};
            return;
        }

        int mid = (left + right) / 2;
        int leftNode = node * 2;
        int rightNode = node * 2 + 1;

        if (idx <= mid) {
            updateTree(idx, leftNode, left, mid);
        } else {
            updateTree(idx, rightNode, mid + 1, right);
        }

        segTree[node] = cmpNode(segTree[leftNode], segTree[rightNode]);

    }
}
