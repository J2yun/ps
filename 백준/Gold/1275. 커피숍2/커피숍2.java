import java.io.*;
import java.util.*;

public class Main {
	static long[] arr, tree;
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());

		arr = new long[N + 1];
		tree = new long[4 * N + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}

		buildTree(1, 1, N);

		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			if (x > y) {
				int tmp = y;
				y = x;
				x = tmp;
			}
			int a = Integer.parseInt(st.nextToken());
			long b = Long.parseLong(st.nextToken());
			sb.append(query(1, x, y, 1, N)).append("\n");
			update(1, a, b, 1, N);
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

	static long query(int node, int L, int R, int left, int right) {
		if (L > right || R < left) {
			return 0;
		}

		if (L <= left && right <= R) {
			return tree[node];
		}

		int mid = (left + right) / 2;
		int leftNode = node * 2;
		int rightNode = node * 2 + 1;

		return query(leftNode, L, R, left, mid) + query(rightNode, L, R, mid + 1, right);
	}

	static void update(int node, int idx, long val, int left, int right) {
		if (left == right) {
			tree[node] = val;
			return;
		}

		int mid = (left + right) / 2;
		int leftNode = node * 2;
		int rightNode = node * 2 + 1;

		if (idx <= mid) {
			update(leftNode, idx, val, left, mid);
		} else {
			update(rightNode, idx, val, mid + 1, right);
		}

		tree[node] = tree[leftNode] + tree[rightNode];
	}

}
