import java.io.*;
import java.util.*;

public class Main {
	static int[][] green = new int[6][4];
	static int[][] blue = new int[6][4];
	static int score = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			if (t == 1) {
				sol(blue, false, c);
				sol(green, false, 3 - r);
			} else if (t == 2) {
				sol(blue, c, c + 1);
				sol(green, true, 3 - r);
			} else {
				sol(blue, true, c);
				sol(green, 3 - r, 2 - r);
			}

			countScore(blue);
			countScore(green);
		}

		System.out.println(score);
		System.out.println(countBlock());
	}

	static void sol(int[][] arr, boolean flag, int c) {
		int r = 5;
		for (int j = 0; j < 5; j++) {
			if (arr[j + 1][c] == 1) {
				r = j;
				break;
			}
		}

		arr[r][c] = 1;
		if (flag) {
			arr[r - 1][c] = 1;
		}

	}

	static void sol(int[][] arr, int c1, int c2) {
		int r = 5;
		for (int j = 0; j < 5; j++) {
			if (arr[j + 1][c1] == 1 || arr[j + 1][c2] == 1) {
				r = j;
				break;
			}
		}

		arr[r][c1] = 1;
		arr[r][c2] = 1;

	}

	static void countScore(int[][] arr) {
		for (int r = 5; r >= 2; r--) {
			int cnt = 0;
			for (int c = 0; c < 4; c++) {
				if (arr[r][c] == 1) {
					cnt++;
				}
			}

			if (cnt == 4) {
				score++;
				for (int rr = r; rr > 0; rr--) {
					arr[rr] = arr[rr - 1];
				}
				arr[0] = new int[4];
				r++;
			}
		}

		int cnt = 0;
		for (int r = 0; r < 2; r++) {
			for (int c = 0; c < 4; c++) {
				if (arr[r][c] == 1) {
					cnt++;
					break;
				}
			}
		}

		if (cnt > 0) {
			for (int r = 5; r >= 2; r--) {
				arr[r] = arr[r - cnt];
			}
		}
		
		for (int r = 0; r < 2; r++) {
			arr[r] = new int[4];
		}
	}

	static int countBlock() {
		int ret = 0;
		for (int r = 2; r < 6; r++) {
			for (int c = 0; c < 4; c++) {
				if (blue[r][c] == 1) {
					ret++;
				}
				if (green[r][c] == 1) {
					ret++;
				}
			}
		}

		return ret;
	}
}
