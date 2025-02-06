import java.io.*;
import java.util.*;

public class Main {
	static boolean[] visit, set;
	static int[] weight;
	static List<List<Integer>> graph = new ArrayList<>();
	static int[][] dp;

	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int N = read();
		visit = new boolean[N + 1];
		weight = new int[N + 1];
		dp = new int[2][N + 1];
		set = new boolean[N + 1];

		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
//		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			weight[i] = read();
		}
		for (int i = 0; i < N - 1; i++) {
//			st = new StringTokenizer(br.readLine());
			int a = read();
			int b = read();

			graph.get(a).add(b);
			graph.get(b).add(a);
		}

		solA(1);
		int max = Math.max(dp[1][1], dp[0][1]);
		sb.append(max).append("\n");

		visit = new boolean[N + 1];
		solB(1, max);
		for (int i = 1; i <= N; i++) {
			if (set[i]) {
				sb.append(i).append(" ");
			}
		}
		System.out.println(sb);
	}

	static void solA(int now) {
		if (visit[now]) {
			return;
		}
		dp[0][now] = 0;
		dp[1][now] = weight[now];
		visit[now] = true;

		for (int next : graph.get(now)) {
			if (visit[next]) {
				continue;
			}
			solA(next);
			dp[0][now] += Math.max(dp[0][next], dp[1][next]);
			dp[1][now] += dp[0][next];
		}
	}

	static void solB(int now, int val) {
		if (visit[now]) {
			return;
		}
		visit[now] = true;
		if (dp[0][now] == val) { // now가 선택되지 않은 케이스
			for (int next : graph.get(now)) {
				if (visit[next]) {
					continue;
				}
				if (dp[0][next] > dp[1][next]) {
					solB(next, dp[0][next]);
				} else {
					solB(next, dp[1][next]);
				}
			}

		} else { // now가 선택된 경우
			set[now] = true;
			for (int next : graph.get(now)) {
				if (visit[next]) {
					continue;
				}
				solB(next, dp[0][next]);
			}
		}

	}
	
    public static int read() throws IOException {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }
}
