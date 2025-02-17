import java.io.*;
import java.util.*;

public class Main {
	static int N, H;
	static int[] depth;
	static int[][] table, disTable;
	static boolean[] visit;
	static List<List<int[]>> graph = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		depth = new int[N + 1];
		visit = new boolean[N + 1];
		H = (int) Math.ceil(Math.log(N) / Math.log(2)) + 1;
		table = new int[H][N + 1];
		disTable = new int[H][N + 1];

		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int dis = Integer.parseInt(st.nextToken());

			graph.get(a).add(new int[] { b, dis });
			graph.get(b).add(new int[] { a, dis });
		}
		bfs(1);
		makeTable();

		StringBuilder sb = new StringBuilder();
		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			sb.append(lca(a, b)).append("\n");
		}

		System.out.println(sb);
	}

	static void bfs(int root) {
		Queue<Integer> que = new LinkedList<>();
		que.add(root);
		visit[root] = true;
		depth[root] = 1;
		while (!que.isEmpty()) {
			int now = que.remove();
			for (int[] tmp : graph.get(now)) {
				int next = tmp[0];
				int dis = tmp[1];
				if (visit[next]) {
					continue;
				}
				table[0][next] = now;
				disTable[0][next] = dis;

				depth[next] = depth[now] + 1;
				visit[next] = true;
				que.add(next);
			}
		}

	}

	static void makeTable() {
		for (int i = 1; i < H; i++) {
			for (int node = 1; node <= N; node++) {
				int tmp = table[i - 1][node];
				table[i][node] = table[i - 1][tmp];

				int tmpDis = disTable[i - 1][node];
				disTable[i][node] = disTable[i - 1][tmp] + tmpDis;
			}
		}

	}

	// 1. 공통 조상 구하기, 2. 각각 공통 조상과, a/b 사이의 거리 구해서 더하기
	static int lca(int a, int b) {
//		System.out.println("lca... " + a + " " + b);
		int aa = 0, bb = 0;

		if (depth[a] < depth[b]) {
			int tmp = a;
			a = b;
			b = tmp;
		}

		for (int i = H - 1; i >= 0; i--) {
			if (depth[a] - depth[b] >= (1 << i)) {
				aa += disTable[i][a];
				a = table[i][a];
			}
		}

//		System.out.printf("%d:%d / %d:%d\n", a, aa, b, bb);

		if (a == b) {
			return aa;
		}

		for (int i = H - 1; i >= 0; i--) {
			if (table[i][a] != table[i][b]) {
				aa += disTable[i][a];
				a = table[i][a];
				bb += disTable[i][b];
				b = table[i][b];
			}
		}

		return aa + bb + disTable[0][a] + disTable[0][b];
	}
}
