import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] tree, lazy;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		tree = new int[4 * N + 1];
		lazy = new int[4 * N + 1];
		buildTree(1, 1, N);

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			if (cmd == 0) {
				updateRange(1, a, b, 1, N);
			} else {
				sb.append(getRange(1, a, b, 1, N)).append("\n");
			}
		}
		System.out.print(sb);
	}

	static void buildTree(int node, int left, int right) {
		if (left == right) {
			return;
		}

		int mid = (left + right) / 2;
		int leftNode = node * 2;
		int rightNode = node * 2 + 1;

		buildTree(leftNode, left, mid);
		buildTree(rightNode, mid + 1, right);

		tree[node] = 0;
	}

	static void lazyRropagation(int node, int left, int right) {
		if (lazy[node] == 1) {
			tree[node] = (right - left + 1) - tree[node];

			if (left != right) {
				lazy[node * 2] = (1 - lazy[node * 2]);
				lazy[node * 2 + 1] = (1 - lazy[node * 2 + 1]);
			}
			lazy[node] = 0;
		}
	}

	static void updateRange(int node, int L, int R, int left, int right) {
		lazyRropagation(node, left, right);

		if (R < left || right < L) {
			return;
		}

		int mid = (left + right) / 2;
		int leftNode = node * 2;
		int rightNode = node * 2 + 1;

		if (L <= left && right <= R) {
			tree[node] = (right - left + 1) - tree[node];
			if (left != right) {
				lazy[leftNode] = (1 - lazy[leftNode]);
				lazy[rightNode] = (1 - lazy[rightNode]);
			}
			return;
		}

		updateRange(leftNode, L, R, left, mid);
		updateRange(rightNode, L, R, mid + 1, right);

		tree[node] = tree[leftNode] + tree[rightNode];
	}

	static int getRange(int node, int L, int R, int left, int right) {
		lazyRropagation(node, left, right);

		if (R < left || right < L) {
			return 0;
		}

		if (L <= left && right <= R) {
			return tree[node];
		}

		int mid = (left + right) / 2;
		int leftNode = node * 2;
		int rightNode = node * 2 + 1;

		return getRange(leftNode, L, R, left, mid) + getRange(rightNode, L, R, mid + 1, right);
	}

}
