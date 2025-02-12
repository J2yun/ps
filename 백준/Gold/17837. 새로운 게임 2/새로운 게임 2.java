import java.io.*;
import java.util.*;

public class Main {
	static int N, K;
	static int[][] arr;
	static int[] dr = { 0, 0, -1, 1 }; // 1234
	static int[] dc = { 1, -1, 0, 0 };
	static int[][] node;
	static ArrayDeque<Integer> board[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new int[N][N];
		node = new int[3][K + 1]; // r,c,dir
		board = new ArrayDeque[N][N];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
				board[r][c] = new ArrayDeque<>();
			}
		}

		for (int i = 1; i <= K; i++) {
			st = new StringTokenizer(br.readLine());
			node[0][i] = Integer.parseInt(st.nextToken()) - 1;
			node[1][i] = Integer.parseInt(st.nextToken()) - 1;
			node[2][i] = Integer.parseInt(st.nextToken()) - 1;
			board[node[0][i]][node[1][i]].add(i);
		}
		int ans = -1;

		for (int turn = 1; turn <= 1000; turn++) {
//			System.out.println("TURN " + turn);
			if (move()) {
				ans = turn;
				break;
			}
		}
		System.out.println(ans);
	}

	static boolean inRange(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < N;
	}

	static int changeDir(int d) {
		if (d == 0) {
			return 1;
		} else if (d == 1) {
			return 0;
		} else if (d == 2) {
			return 3;
		} else {
			return 2;
		}
	}

	static boolean move() {
		for (int i = 1; i <= K; i++) {
			int r = node[0][i];
			int c = node[1][i];
			int d = node[2][i];

//			System.out.print(r + "," + c + " " + board[r][c]);

			int nr = r + dr[d];
			int nc = c + dc[d];

			// (r,c)에서 i번이 몇번째에 있는지 알아야 함
			ArrayDeque<Integer> group = new ArrayDeque<>();
			while (true) {
				int now = board[r][c].removeLast();
				group.add(now);
				if (now == i) {
					break;
				}
			}
//			System.out.printf(" [%d,%d -> %d,%d] inRange: %b\n", r, c, nr, nc, inRange(nr,nc));

			if (!inRange(nr, nc) || arr[nr][nc] == 2) {
				d = changeDir(d);
				node[2][i] = d;
				nr = r + dr[d];
				nc = c + dc[d];				
				if (!inRange(nr, nc) || arr[nr][nc] == 2) {
					nr = r;
					nc = c;
					while (!group.isEmpty()) {
						int n = group.removeLast();
						node[0][n] = nr;
						node[1][n] = nc;
						board[nr][nc].add(n);
					}
				} else {
					if (arr[nr][nc] == 1) {
						while (!group.isEmpty()) {
							int n = group.removeFirst();
							node[0][n] = nr;
							node[1][n] = nc;
							board[nr][nc].add(n);
						}
					} else {
						while (!group.isEmpty()) {
							int n = group.removeLast();
							node[0][n] = nr;
							node[1][n] = nc;
							board[nr][nc].add(n);
						}
					}
				}
				// 이동하게 될 칸이 빨강인 경우 가장 먼저 처리
				// 안해도 된다네요
//				while (!group.isEmpty()) {
//					int n = group.removeLast();
//					node[0][n] = nr;
//					node[1][n] = nc;
//					board[nr][nc].add(n);
//				}
				// 빨강이 아닌 경우는
				// 이동하지 않은 경우 i의 방향만 바꿈
				// 이동한 경우 파란색, 하얀색인 경우 별일 없고/ 빨간색인 경우 순서 변경 /

			} else if (arr[nr][nc] == 1) {
				while (!group.isEmpty()) {
					int n = group.removeFirst();
					node[0][n] = nr;
					node[1][n] = nc;
					board[nr][nc].add(n);
				}
			} else {
				while (!group.isEmpty()) {
					int n = group.removeLast();
					node[0][n] = nr;
					node[1][n] = nc;
					board[nr][nc].add(n);
				}
			}
//			System.out.println(" -> " + nr + "," + nc + " " + board[nr][nc]);
			if (board[nr][nc].size() >= 4) {
				return true;
			}

		}
		return false;
	}
}

// 말 하나하나 움직이는 구현하면서 고려해야될 사항
// 이동한 칸이 파란색일 경우 반대로 2칸 이동(벽 고려해야함)
// 이동한 칸이 빨간색일 경우 옮긴놈들 쌓은 순서 변경
