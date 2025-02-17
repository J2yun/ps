import java.io.*;
import java.util.*;

public class Main {
	static int[][] arr;
	static int R, C;
	static boolean[][] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		arr = new int[R][C];

		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int clock = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());

			turn(n, clock, k);

//			for (int[] dd : arr) {
//				System.out.println(Arrays.stream(dd).boxed().collect(Collectors.toList()));
//			}
//			System.out.println();
		}
		System.out.print(getSum(arr));

	}

	static int getSum(int[][] tmp) {
		int sum = 0;
		for (int[] ii : tmp) {
			for (int i : ii) {
				sum += i;
			}
		}

		return sum;
	}

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static boolean inRange(int r, int c) {
		return (0 <= r & r < R) && (0 <= c && c <= C);
	}

	static void turn(int n, int clock, int k) {
		k = k % C;
		int[][] tmp = new int[R][C + 1];

		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				tmp[r][c] = arr[r][c];
			}
		}

		for (int r = n - 1; r < R; r += n) {
			int idx = 0;
			if (clock == 0) { // 시계 방향
				for (int c = C - k; c < C; c++) {
					tmp[r][idx++] = arr[r][c];
				}
				for (int c = 0; c < C - k; c++) {
					tmp[r][idx++] = arr[r][c];
				}

			} else { // 반 시계 방향
				for (int c = k; c < C; c++) {
					tmp[r][idx++] = arr[r][c];
				}
				for (int c = 0; c < k; c++) {
					tmp[r][idx++] = arr[r][c];
				}

			}
		}

		for (int r = 0; r < R; r++) {
			tmp[r][C] = tmp[r][0];
		}



		visit = new boolean[R][C + 1];
		boolean isChange = false;

		for (int r = 0; r < R; r++) {
			for (int c = 0; c <= C; c++) {
				if (tmp[r][c] == 0 || visit[r][c]) {
					continue;
				}

				for (int i = 0; i < 4; i++) {
					int nr = r + dr[i];
					int nc = c + dc[i];

					if (inRange(nr, nc) && tmp[nr][nc] == tmp[r][c]) {
						isChange = true;
						// BFS
						bfs(r, c, tmp);
					}

				}

			}
		}
//		System.out.println("flag! " + isChange);
//		for (int[] dd : tmp) {
//			System.out.println(Arrays.stream(dd).boxed().collect(Collectors.toList()));
//		}
//		System.out.println();
		if (isChange) {
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					if (visit[r][c]) {
						arr[r][c] = 0;
					} else {
						arr[r][c] = tmp[r][c];
					}
				}
			}
			for (int r = 0; r < R; r++) {
				if (visit[r][C]) {
					arr[r][0] = 0;
				}
			}

		} else {
			double sum = 0, cnt = 0;
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					if (tmp[r][c] > 0) {
						sum += tmp[r][c];
						cnt++;
					}
				}
			}

			double avg = sum / cnt;
//			System.out.println(avg + " " + (5.0 == 5));

			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					if (tmp[r][c] == 0) {
						arr[r][c] = 0;
						continue;
					}

					if (avg < tmp[r][c]) {
						arr[r][c] = tmp[r][c] - 1;
					} else if (avg > tmp[r][c]) {
						arr[r][c] = tmp[r][c] + 1;
					} else {
						arr[r][c] = tmp[r][c];
					}
				}
			}
		}
	}

	static void bfs(int r, int c, int[][] tmp) {
		Queue<int[]> que = new LinkedList<>();
		que.add(new int[] { r, c });
		visit[r][c] = true;

		while (!que.isEmpty()) {
			int[] rc = que.remove();
			int rr = rc[0];
			int cc = rc[1];

			for (int i = 0; i < 4; i++) {
				int nr = rr + dr[i];
				int nc = cc + dc[i];

				if (inRange(nr, nc) && !visit[nr][nc] && tmp[nr][nc] == tmp[r][c]) {
					que.add(new int[] { nr, nc });
					visit[nr][nc] = true;
				}
			}
		}
	}
}
