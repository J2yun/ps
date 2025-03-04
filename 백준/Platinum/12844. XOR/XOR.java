import java.io.*;
import java.util.*;

public class Main {
	static int[] tree, arr, lazy;
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		tree = new int[4 * N + 1];
		lazy = new int[4 * N + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		buildTree(1, 0, N - 1);

		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (cmd == 1) {
				int k = Integer.parseInt(st.nextToken());
				updateRange(1, a, b, 0, N - 1, k);
			} else {
				sb.append(query(1, a, b, 0, N - 1)).append("\n");
			}
		}

		System.out.print(sb);
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

		tree[node] = tree[leftNode] ^ tree[rightNode];
	}

	static void lazyPropagation(int node, int left, int right) {
		if (lazy[node] > 0) {
			if ((right - left) % 2 == 0) {
				tree[node] = tree[node] ^ lazy[node];
			}

			if (left != right) {
				lazy[node * 2] = lazy[node * 2] ^ lazy[node];
				lazy[node * 2 + 1] = lazy[node * 2 + 1] ^ lazy[node];
			}
			lazy[node] = 0;
		}
	}

	static void updateRange(int node, int L, int R, int left, int right, int val) {
		lazyPropagation(node, left, right);

		if (right < L || R < left) {
			return;
		}

		int leftNode = node * 2;
		int rightNode = node * 2 + 1;
		int mid = (left + right) / 2;

		if (L <= left && right <= R) {
			if ((right - left) % 2 == 0) {
				tree[node] = tree[node] ^ val;
			}
//			tree[node] = tree[node] ^ val;
			if (left != right) {
				lazy[leftNode] = lazy[leftNode] ^ val;
				lazy[rightNode] = lazy[rightNode] ^ val;
			}
			return;
		}

		updateRange(leftNode, L, R, left, mid, val);
		updateRange(rightNode, L, R, mid + 1, right, val);

		tree[node] = tree[leftNode] ^ tree[rightNode];
	}

	static int query(int node, int L, int R, int left, int right) {
		lazyPropagation(node, left, right);

		if (right < L || R < left) {
			return 0;
		}

		int leftNode = node * 2;
		int rightNode = node * 2 + 1;
		int mid = (left + right) / 2;

		if (L <= left && right <= R) {
			return tree[node];
		}

		return query(leftNode, L, R, left, mid) ^ query(rightNode, L, R, mid + 1, right);
	}

}
