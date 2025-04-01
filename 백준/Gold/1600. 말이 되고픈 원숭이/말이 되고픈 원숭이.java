import java.io.*;
import java.util.*;

public class Main {
	static int R, C, K;
	static int[][][] arr;
	static ArrayDeque<int[]> que = new ArrayDeque<>();
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int[] hr = { -2, -1, 1, 2, 2, 1, -1, -2 };
	static int[] hc = { 1, 2, 2, 1, -1, -2, -2, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		K = Integer.parseInt(br.readLine()) + 1;
		st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		arr = new int[K][R][C];
		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				int tmp = Integer.parseInt(st.nextToken());
				if (tmp == 1) {
					for (int k = 0; k < K; k++) {
						arr[k][r][c] = -1;
					}
				}
			}
		}

		que.add(new int[] { 0, 0, 0 });
		arr[0][0][0] = 1;
		while (!que.isEmpty()) {
			int[] tmp = que.removeFirst();
			int k = tmp[0], r = tmp[1], c = tmp[2];
//			System.out.println(k + " " + r + " " + c);

			if (r == R - 1 && c == C - 1) {
				System.out.println(arr[k][r][c] - 1);
				return;
			}

			// 상하좌우
			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];

				if (inRange(nr, nc) && arr[k][nr][nc] == 0) {
					arr[k][nr][nc] = arr[k][r][c] + 1;
					que.add(new int[] { k, nr, nc });
				}
			}
			// 말
			if (k + 1 < K) {
				for (int i = 0; i < 8; i++) {
					int nr = r + hr[i];
					int nc = c + hc[i];

					if (inRange(nr, nc) && arr[k + 1][nr][nc] == 0) {
						arr[k + 1][nr][nc] = arr[k][r][c] + 1;
						que.add(new int[] { k + 1, nr, nc });
					}
				}
			}

		}

		System.out.println(-1);
	}

	static boolean inRange(int r, int c) {
		return 0 <= r && r < R && 0 <= c && c < C;
	}
}
