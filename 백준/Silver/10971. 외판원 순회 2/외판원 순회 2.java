import java.io.*;
import java.util.*;

public class Main {
	static int[][] dp[], dis;
	static int N;
	static final int INF = 100_000_000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		dp = new int[N][N][1 << N]; // 시작지점, cur, visit;
		dis = new int[N][N];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				int tmp = Integer.parseInt(st.nextToken());
				dis[r][c] = tmp == 0 ? INF : tmp;
			}
		}

		int ans = INF;
		for (int i = 0; i < N; i++) {
			ans = Math.min(ans, tsp(i, i, 1 << i));
		}

		System.out.println(ans);
	}

	static int tsp(int start, int cur, int visit) {
		if (visit == (1 << N) - 1) {
			if (dis[cur][start] == 0) {
				return INF;
			}
			return dis[cur][start];
		}

		if (dp[start][cur][visit] != 0) {
			return dp[start][cur][visit];
		}

		int distance = INF;
		for (int i = 0; i < N; i++) {
			if (((1 << i) & visit) > 0) {
				continue;
			}

			distance = Math.min(distance, tsp(start, i, visit | (1 << i)) + dis[cur][i]);
		}
		dp[start][cur][visit] = distance;

		return distance;
	}
}
