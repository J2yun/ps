import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
	static int[][] cnt, arr;
	static boolean[][] visit;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int R, C;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		cnt = new int[R][C];
		arr = new int[R][C];
		visit = new boolean[R][C];

		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
                cnt[r][c] = -1;
			}
		}

		System.out.print(dfs(0, 0));
	}

	static boolean inRange(int r, int c) {
		return 0 <= r && r < R && 0 <= c && c < C;
	}

	static int dfs(int r, int c) {
		if (r == R - 1 && c == C - 1) {
			return 1;
		}
		if (cnt[r][c] != -1) {
			return cnt[r][c];
		}

		cnt[r][c] = 0;
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if (!inRange(nr, nc))
				continue;

			if (arr[nr][nc] < arr[r][c]) {
				cnt[r][c] += dfs(nr, nc);
			}
		}

		return cnt[r][c];
	}
}
