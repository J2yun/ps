import java.io.*;
import java.util.*;

public class Main {
	static int[][] dp, dis;
	static int N;
	static final int INF = 100_000_000;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = read();
		dp = new int[N][1 << N]; // 시작지점, cur, visit;
		dis = new int[N][N];

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				dis[r][c] = read();
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
			if (((1 << i) & visit) > 0 || dis[cur][i ] == 0) {
				continue;
			}

			distance = Math.min(distance, tsp(i, visit | (1 << i)) + dis[cur][i]);
		}
		dp[cur][visit] = distance;

		return distance;
	}
    
    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48)
            n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13)
            System.in.read();
        return n;
    }
}
