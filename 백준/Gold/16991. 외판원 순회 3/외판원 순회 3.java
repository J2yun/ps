
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static double[][] dis, dp;
	static int[][] point;
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		point = new int[2][N];
		dis = new double[N][N];
		dp = new double[N][1 << N];
//			for (int i = 0; i < N; i++) {
//				Arrays.fill(dp[i], INF);
//			}


		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			point[0][i] = Integer.parseInt(st.nextToken());
			point[1][i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < N - 1; i++) {
			for (int j = i + 1; j < N; j++) {
				double distance = Math
						.sqrt(Math.pow(point[0][i] - point[0][j], 2) + Math.pow(point[1][i] - point[1][j], 2));
				dis[i][j] = distance;
				dis[j][i] = distance;
			}
		}

		System.out.println(dfs(0,1));
	}

	static double dfs(int now, int visit) {
//		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.stream(dp[i]).boxed().collect(Collectors.toList()));
//		}
		if (visit == (1 << N) - 1) {
			return dis[now][0];
		}

		if (dp[now][visit] != 0) {
			return dp[now][visit];
		}

		double distance = Double.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			if ((visit & (1 << i)) > 0) {
				continue;
			}

			distance = Math.min(distance, dfs(i, (1 << i | visit)) + dis[i][now]);

		}

		return dp[now][visit] = distance;
	}

}
