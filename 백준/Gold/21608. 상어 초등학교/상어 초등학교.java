import java.io.*;
import java.util.*;

public class Main {
	static class Candidate {
		int like;
		int empty;
		int r;
		int c;

		public Candidate(int like, int empty, int r, int c) {
			super();
			this.like = like;
			this.empty = empty;
			this.r = r;
			this.c = c;
		}
	}

	static int N;
	static int arr[][];
	// 1. 인접한 칸에서 좋아하는 학생 수 2. 인접 칸 중 비어있는 칸 수 3. 행 작/열 작
	static PriorityQueue<Candidate> pq = new PriorityQueue<>((a, b) -> {
		if (a.like == b.like) {
			if (a.empty == b.empty) {
				if (a.r == b.r) {
					return Integer.compare(a.c, b.c);
				} else {
					return Integer.compare(a.r, b.r);
				}
			} else {
				return Integer.compare(b.empty, a.empty);
			}
		} else {
			return Integer.compare(b.like, a.like);
		}
	});
	static HashSet<Integer>[] likeSet;

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static boolean inRange(int r, int c) {
		return (0 <= r && r < N) && (0 <= c && c < N);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		likeSet = new HashSet[N * N + 1];
		for (int i = 0; i <= N * N; i++) {
			likeSet[i] = new HashSet<>();
		}
		int[] order = new int[N * N + 1];
		for (int i = 1; i <= N * N; i++) {
			st = new StringTokenizer(br.readLine());
			order[i] = Integer.parseInt(st.nextToken());
			for (int j = 0; j < 4; j++) {
				likeSet[order[i]].add(Integer.parseInt(st.nextToken()));
			}
		}

		for (int i = 1; i <= N * N; i++) {
			pq.clear();

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (arr[r][c] > 0) {
						continue;
					}
					int cntEmpty = 0;
					int cntLike = 0;
					for (int d = 0; d < 4; d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];
						if (!inRange(nr, nc)) {
							continue;
						}

						if (arr[nr][nc] == 0) {
							cntEmpty++;
						}
						if (likeSet[order[i]].contains(arr[nr][nc])) {
							cntLike++;
						}
					}
					pq.add(new Candidate(cntLike, cntEmpty, r, c));
				}
			}

			Candidate cand = pq.remove();
			arr[cand.r][cand.c] = order[i];
		}

		int score = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				int cnt = 0;
				for (int i = 0; i < 4; i++) {
					int nr = r + dr[i];
					int nc = c + dc[i];

					if (inRange(nr, nc) && likeSet[arr[r][c]].contains(arr[nr][nc])) {
						cnt++;
					}
				}
				if (cnt > 0) {
					score += Math.pow(10, cnt - 1);
				}
			}
		}
		System.out.println(score);
	}
}
