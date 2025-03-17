import java.io.*;
import java.util.*;

public class Main {
	static int[] arr;
	static boolean[] visit;
	static HashSet<Integer>[] graph;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		visit = new boolean[N + 1];
		arr = new int[N];
		graph = new HashSet[N + 1];

		for (int i = 0; i <= N; i++) {
			graph[i] = new HashSet<>();
		}

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			graph[b].add(a);
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		if (arr[0] != 1) {
			System.out.println(0);
			return;
		}

		int idx = 1;
		visit[1] = true;
		Queue<Integer> que = new LinkedList<>();
		que.add(1);
		while (!que.isEmpty()) {
//			System.out.println(que);
			int now = que.remove();

			while (!graph[now].isEmpty()) {
				if (!graph[now].contains(arr[idx])) {
					System.out.println(0);
					return;
				}
				que.add(arr[idx]);
				visit[arr[idx]] = true;
				graph[arr[idx]].remove(now);
				graph[now].remove(arr[idx++]);

				if (idx == N - 1) {
					System.out.println(1);
					return;
				}
			}
		}

		System.out.println(1);
	}
}
