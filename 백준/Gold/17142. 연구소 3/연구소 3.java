import java.io.*;
import java.util.*;

public class Main {
	static int N, M, vSize, ans = Integer.MAX_VALUE;
	static int[][] arr, tmpArr;
	static boolean[][] visit, vArr;
	static List<Pair> virus = new ArrayList<>();
//	static boolean[] active;
	static int[] dr = { 0, 0, 1, -1 };
	static int[] dc = { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][N];
		vArr = new boolean[N][N];
//		visit = new boolean[N][N];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				int tmp = Integer.parseInt(st.nextToken());
				if (tmp == 2) {
					virus.add(new Pair(r, c));
					vArr[r][c] = true;
				} else if (tmp == 1) {
					arr[r][c] = tmp;
					vArr[r][c] = true;
				} else {
					arr[r][c] = -1;
				}
			}
		}

		vSize = virus.size();
		back(-1, new ArrayDeque<>());
//		ArrayDeque<Integer> l = new ArrayDeque<>();
//		l.add(1);
//		l.add(2);
//		l.add(4);
//		l.add(6);
//		l.add(8);
//
//		solution(l);

		System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);

	}

	static void back(int now, ArrayDeque<Integer> his) {
		if (his.size() == M) {
			solution(his);
		}
		for (int i = now + 1; i < vSize; i++) {
			his.add(i);
			back(i, his);
			his.removeLast();
		}
	}

	static boolean inRange(int r, int c) {
		return (0 <= r && r < N) && (0 <= c && c < N);
	}

	static void solution(ArrayDeque<Integer> v) {
//		System.out.println(v);
		visit = new boolean[N][N];
		tmpArr = new int[N][N];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				tmpArr[r][c] = arr[r][c];
			}

		}

		Queue<Pair> que = new LinkedList<>();
//		Queue<Pair> tmp = new LinkedList<>();

		for (int idx : v) {
			Pair p = virus.get(idx);
			que.add(p);
			visit[p.r][p.c] = true;
			tmpArr[p.r][p.c] = 0;
		}

		while (!que.isEmpty()) {
			Pair now = que.remove();
			int r = now.r;
			int c = now.c;

			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];

				if (inRange(nr, nc) && tmpArr[nr][nc] != 1 && !visit[nr][nc]) {
					tmpArr[nr][nc] = tmpArr[r][c] + 1;
					visit[nr][nc] = true;
					que.add(new Pair(nr, nc));
				}
			}
		}

//		for (int[] ii : tmpArr) {
//			for (int i : ii) {
//				System.out.printf("%d ", i);
//			}
//			System.out.println();
//		}

		int min = getMin();
		if (min != -1 && min < ans) {
			ans = min;
		}

		// Pair tmp 부분을 바이러스로 해야됨
		// BFS 큐에 넣기
		// BFS 돌리면서 모든 바이러스가 활성화되는 시점
		// 활성화되면 그놈도 큐에 들어가서 퍼짐
		// 1. 좌표 계속 que에 때려박기 시간 별로
		// -> 최대 2500개까지들어갈 수 있음

	}

	static int getMin() {
		int min = 0;

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (vArr[r][c]) {
					continue;
				}
				if (tmpArr[r][c] == -1) {
					return -1;
				}
				if (tmpArr[r][c] > min) {
					min = tmpArr[r][c];
				}
			}
		}
		return min;
	}

	static class Pair {
		int r;
		int c;

		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}