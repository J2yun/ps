import java.io.*;
import java.util.*;

public class Main {
	static boolean flag;
	static int R, C;
	static int[][] arr;
	static int[] dr = { -1, 0, 1 };
	static int[] dc = { 1, 1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		arr = new int[R][C];
		for (int r = 0; r < R; r++) {
			char[] tmp = br.readLine().toCharArray();
			for (int c = 0; c < C; c++) {
				if (tmp[c] == 'x') {
					arr[r][c] = -1;
				}
			}
		}

		for (int r = 0; r < R; r++) {
			flag = false;
			dfs(r, 0);
		}

		int ans = 0;
		for (int i = 0; i < R; i++) {
			if (arr[i][C - 1] == 1) {
				ans++;
			}
		
		}
		System.out.println(ans);
	}

	static void dfs(int r, int c) {
		if (c == C - 1) {
			flag = true;
			return;
		}

		for (int i = 0; i < 3; i++) {
			int nr = r + dr[i];

			if (inRange(nr) && arr[nr][c + 1] == 0) {
				arr[nr][c + 1] = 1;
				dfs(nr, c + 1);
//				if (!flag)
//					arr[nr][c + 1] = 0;
				if (flag) {
					break;
				}
//				break;
			}
		}
	}

	static boolean inRange(int r) {
		return 0 <= r && r < R;
	}
}
