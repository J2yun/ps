import java.io.*;
import java.util.*;

public class Main {
	static int[][] dp, dis;
	static int N;
	static final int INF = 100_000_000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		dp = new int[N][1 << N]; // 시작지점, cur, visit;
		dis = new int[N][N];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				int tmp = Integer.parseInt(st.nextToken());
				dis[r][c] = tmp == 0 ? INF : tmp;
			}
		}

		System.out.println(tsp(0, 1));
	}

	static int tsp(int cur, int visit) {
		if (visit == (1 << N) - 1) {
			if (dis[cur][0] == 0) {
				return INF;
			}
			return dis[cur][0];
		}

		if (dp[cur][visit] != 0) {
			return dp[cur][visit];
		}

		int distance = INF;
		for (int i = 0; i < N; i++) {
			if (((1 << i) & visit) > 0) {
				continue;
			}

			distance = Math.min(distance, tsp(i, visit | (1 << i)) + dis[cur][i]);
		}
		dp[cur][visit] = distance;

		return distance;
	}
}
