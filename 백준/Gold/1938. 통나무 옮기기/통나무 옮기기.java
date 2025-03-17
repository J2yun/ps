import java.io.*;
import java.util.*;

public class Main {
	static int N;
	// [방향][r][c]
	static int[][][] arr;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static ArrayList<int[]> bb, ee;

	public static void main(String[] args) throws IOException {
		// --------------솔루션 코드를 작성하세요.--------------------------------
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		arr = new int[2][N][N];
		bb = new ArrayList<>();
		ee = new ArrayList<>();

		for (int r = 0; r < N; r++) {
			String s = br.readLine();
			for (int c = 0; c < N; c++) {
				char cc = s.charAt(c);
				if (cc == 'B') {
					bb.add(new int[] { r, c });
				} else if (cc == 'E') {
					ee.add(new int[] { r, c });
				} else if (cc - '0' == 1) {
					arr[0][r][c] = -1;
					arr[1][r][c] = -1;
				}
			}
		}

		Queue<int[]> que = new LinkedList<>();

		// 시작지점 구하기
		Collections.sort(bb, (a, b) -> {
			if (a[0] == b[0]) {
				return Integer.compare(a[1], b[1]);
			} else {
				return Integer.compare(a[0], b[0]);
			}
		});

		int[] tmp = bb.get(1);
		int sr = tmp[0], sc = tmp[1], sDim;
		if (bb.get(0)[0] == bb.get(1)[0]) {
			que.add(new int[] { 1, sr, sc });
			sDim = 1;
		} else {
			que.add(new int[] { 0, sr, sc });
			sDim = 0;
		}

		// 끝지점 구하기
		Collections.sort(ee, (a, b) -> {
			if (a[0] == b[0]) {
				return Integer.compare(a[1], b[1]);
			} else {
				return Integer.compare(a[0], b[0]);
			}
		});

		tmp = ee.get(1);
		int er = tmp[0], ec = tmp[1], eDim;
		if (ee.get(0)[0] == ee.get(1)[0]) {
			eDim = 1;
		} else {
			eDim = 0;
		}

		arr[sDim][sr][sc] = 1;

		// BFS
		while (!que.isEmpty()) {
			tmp = que.remove();
			int dim = tmp[0];
			int r = tmp[1];
			int c = tmp[2];
//			System.out.println(dim + " " + r + "," + c);
			if (dim == eDim && r == er && c == ec) {
				break;
			}

			for (int[] n : getNext(dim, r, c)) {
				int nDim = n[0];
				int nr = n[1];
				int nc = n[2];
//				System.out.println(nDim + " " + nr + "," + nc);

				if (arr[nDim][nr][nc] == 0) {
					arr[nDim][nr][nc] = arr[dim][r][c] + 1;
					que.add(new int[] { nDim, nr, nc });
				}

			}
		}

//		for (int dim = 0; dim < 2; dim++) {
//			System.out.println("DIM " + dim);
//			for (int r = 0; r < N; r++) {
//				for (int c = 0; c < N; c++) {
//					System.out.printf("%d ", arr[dim][r][c]);
//				}
//				System.out.println();
//			}
//			System.out.println();
//		}

		System.out.println(arr[eDim][er][ec] == 0 ? 0 : arr[eDim][er][ec]-1);
	}

	static boolean inRange(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < N;
	}

	static ArrayList<int[]> getNext(int dim, int r, int c) {
		ArrayList<int[]> ret = new ArrayList<>();

		if (dim == 0) {
			if (inRange(r - 2, c) && arr[dim][r - 2][c] != -1) {
				ret.add(new int[] { 0, r - 1, c });
			}
			if (inRange(r + 2, c) && arr[dim][r + 2][c] != -1) {
				ret.add(new int[] { 0, r + 1, c });
			}
			boolean flag = true;
			for (int i = -1; i < 2; i++) {
				if (!inRange(r + i, c - 1) || arr[dim][r + i][c - 1] == -1) {
					flag = false;
					break;
				}
			}
			if (flag) {
				ret.add(new int[] { 0, r, c - 1 });
			}
			flag = true;
			for (int i = -1; i < 2; i++) {
//				System.out.println(
//						(r + i) + "/" + (c + 1) + !inRange(r + i, c + 1) + " " + (arr[dim][r + i][c + 1] == -1));
				if (!inRange(r + i, c + 1) || arr[dim][r + i][c + 1] == -1) {
					flag = false;
					break;
				}
			}
			if (flag) {
				ret.add(new int[] { 0, r, c + 1 });
			}
			flag = true;
			for (int i = -1; i < 2; i++) {
				for (int j = -1; j < 2; j++) {
					if (!inRange(r + i, c + j) || arr[dim][r + i][c + j] == -1) {
						flag = false;
						break;
					}
				}
			}
			if (flag) {
				ret.add(new int[] { 1, r, c });
			}
		} else {

			boolean flag = true;
			for (int i = -1; i < 2; i++) {
				if (!inRange(r - 1, c + i) || arr[dim][r - 1][c + i] == -1) {
					flag = false;
					break;
				}
			}
			if (flag) {
				ret.add(new int[] { 1, r - 1, c });
			}
			flag = true;
			for (int i = -1; i < 2; i++) {
				if (!inRange(r + 1, c + i) || arr[dim][r + 1][c + i] == -1) {
					flag = false;
					break;
				}
			}
			if (flag) {
				ret.add(new int[] { 1, r + 1, c });
			}

			if (inRange(r, c - 2) && arr[dim][r][c - 2] != -1) {
				ret.add(new int[] { 1, r, c - 1 });
			}
			if (inRange(r, c + 2) && arr[dim][r][c + 2] != -1) {
				ret.add(new int[] { 1, r, c + 1 });
			}

			flag = true;
			for (int i = -1; i < 2; i++) {
				for (int j = -1; j < 2; j++) {
					if (!inRange(r + i, c + j) || arr[dim][r + i][c + j] == -1) {
						flag = false;
						break;
					}
				}
			}
			if (flag) {
				ret.add(new int[] { 0, r, c });
			}
		}

		return ret;
	}

}
