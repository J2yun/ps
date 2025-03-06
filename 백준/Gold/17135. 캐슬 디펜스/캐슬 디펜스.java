import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
	static int[][] arr, backup;
	static int R, C, D, turn, cnt = 0, cmp;
	static int[] dr = { 0, -1, 0 };
	static int[] dc = { -1, 0, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		arr = new int[R][C];
		backup = new int[R][C];
		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				int tmp = Integer.parseInt(st.nextToken());
				if (turn == 0 && tmp == 1) {
					turn = R - r;
				}
				arr[r][c] = tmp;
				backup[r][c] = tmp;
			}
		}

		back(0, -1, 0);

		System.out.print(cnt);
	}

	static boolean inRange(int r, int c) {
		return 0 <= r && r < R && 0 <= c && c < C;
	}

	static void back(int depth, int now, int visit) {
		if (depth == 3) {
//			System.out.println(Integer.toBinaryString(visit));
			playGame(visit);
			return;
		}

		for (int i = now + 1; i < C; i++) {
			if (((1 << i) & visit) == 0) {
				back(depth + 1, i, visit | (1 << i));
			}
		}
	}

	static void playGame(int mask) {
		cmp = 0;
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				arr[r][c] = backup[r][c];
			}
		}
//		System.out.println(Integer.toBinaryString(mask));
		for (int t = 0; t < turn; t++) {
			// 1. 궁수가 놓여져 있는 3개의 칸 설정
			// 2. 궁수별로 제거하게 되는 적 정하기
			int[][] target = new int[3][2];

			int idx = 0;
			for (int i = 0; i < C; i++) {
				if (((1 << i) & mask) > 0) {
					target[idx++] = getTarget(i);
				}
			}
			// 3. 실제로 제거하기
			for (int i = 0; i < 3; i++) {
				if (target[i][0] == -1) {
					continue;
				}
				int tr = target[i][0], tc = target[i][1];
				if (arr[tr][tc] == 1) {
					arr[tr][tc] = 0;
					cmp++;
				}
			}

			// 4. 적들 아래로 한칸씩 이동하기
			for (int r = R - 1; r > 0; r--) {
				arr[r] = arr[r - 1];
			}
			arr[0] = new int[C];
		}

		if (cnt < cmp) {
			cnt = cmp;
		}
	}

	static int[] getTarget(int start) {
		Queue<int[]> que = new LinkedList<>();
		que.add(new int[] { R - 1, start, 0 });

		while (!que.isEmpty()) {
			int[] tmp = que.remove();
			int r = tmp[0];
			int c = tmp[1];
			int depth = tmp[2];

			if (depth == D) {
				break;
			}

			if (arr[r][c] == 1) {
				return new int[] { r, c };
			}

			for (int i = 0; i < 3; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];

				if (inRange(nr, nc)) {
					que.add(new int[] { nr, nc, depth + 1 });
				}
			}
		}

		return new int[] { -1, -1 };
	}
}
