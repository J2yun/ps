import java.io.*;
import java.util.*;


public class Main {
	static int[] arr, tree;
	static int n;
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while (true) {
			String inp[] = br.readLine().split(" ");
			n = Integer.parseInt(inp[0]);
			if (n == 0) break;
			
			arr = new int[n + 1];
			tree = new int[4* n];
			for (int i = 1; i <= n; i++) {
				arr[i] = Integer.parseInt(inp[i]);
			}
			arr[0] = Integer.MAX_VALUE;
			buildTree(1, 1, n);
//			System.out.println(getMin(1,7));
			sb.append(getMaxSquare(1, n)).append("\n");
		}
		
		System.out.print(sb);
	}
	
	public static int buildTree(int node, int left, int right) {
		if (left == right) {
//			System.out.printf("tree[%d]: %d\n",node,left);
			tree[node] = left;
			return left;
		}
		
		int leftNode = node * 2;
		int rightNode = node * 2 + 1;
		int mid = (left + right) / 2;
		
		int a = buildTree(leftNode, left, mid);
		int b = buildTree(rightNode, mid + 1, right);
		
		if (arr[a] > arr[b]) {
			return tree[node] = b;
		} else {
			return tree[node] = a;
		}
	}
	
	public static int getMin(int left, int right) {
		return getMin(1, 1, n, left, right);
	}
	
	public static int getMin(int node, int L, int R, int left, int right) {
		if (R < left || right < L) {
			return 0;
		}
		
		if (left <= L && R <= right) {
//			System.out.printf("%d - %d: %d\n",L, R, tree[L]);
			return tree[node];
		}
		
		int leftNode = node * 2;
		int rightNode = node * 2 + 1;
		int mid = (L + R) / 2;
		
		int a = getMin(leftNode, L, mid, left, right);
		int b = getMin(rightNode, mid + 1, R, left, right);
		
		if (arr[a] > arr[b]) {
			return b;
		} else {
			return a;
		}
	}
	
	// Divide and Conquer
	public static long getMaxSquare(int left, int right) {
		if (left > right) return 0;
		int min_idx = getMin(left, right);
		
		long max_area = arr[min_idx] * (long) (right - left + 1);
		
		max_area = Math.max(max_area, getMaxSquare(left, min_idx - 1));
		max_area = Math.max(max_area, getMaxSquare(min_idx+1, right));
		
		return max_area;
		
		
	}
}
