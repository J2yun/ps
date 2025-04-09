import java.io.*;
import java.util.*;

public class Main {
	static int[][] arr;
	static int N, X;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        arr = new int[N][N];

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                arr[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = 0;

        boolean flag;
        boolean[] visit;
        for (int r = 0; r < N; r++) {
            flag = false;
            visit = new boolean[N];
            for (int c = 0; c < N - 1; c++) {
                if (arr[r][c] < arr[r][c + 1]) {
                    if (arr[r][c] + 1 != arr[r][c + 1]) {
                        flag = true;
                        break;
                    }
                    int tmp = arr[r][c];
                    for (int i = 0; i < X; i++) {
                        if (!inRange(r, c - i) || tmp != arr[r][c - i] || visit[c - i]) {
                            //								System.out.println("!");
                            flag = true;
                            break;
                        }
                        visit[c - i] = true;
                    }
                } else if (arr[r][c] > arr[r][c + 1]) {
                    if (arr[r][c] != arr[r][c + 1] + 1) {
                        flag = true;
                        break;
                    }
                    int tmp = arr[r][c + 1];
                    for (int i = 0; i < X; i++) {
                        if (!inRange(r, c + 1 + i) || tmp != arr[r][c + 1 + i] || visit[c + 1 + i]) {
                            flag = true;
                            break;
                        }
                        visit[c + 1 + i] = true;
                    }
                }
            }
            if (!flag) {
                //					System.out.println(r + "이 만들 수 있음");
                cnt++;
            }
        }

        for (int c = 0; c < N; c++) {
            flag = false;
            visit = new boolean[N];
            for (int r = 0; r < N - 1; r++) {
                if (arr[r][c] < arr[r + 1][c]) {
                    if (arr[r][c] + 1 != arr[r + 1][c]) {
                        flag = true;
                        break;
                    }
                    int tmp = arr[r][c];
                    for (int i = 0; i < X; i++) {
                        if (!inRange(r - i, c) || tmp != arr[r - i][c] || visit[r - i]) {
                            //								System.out.println("!");
                            flag = true;
                            break;
                        }
                        visit[r - i] = true;
                    }
                } else if (arr[r][c] > arr[r + 1][c]) {
                    if (arr[r][c] != arr[r + 1][c] + 1) {
                        flag = true;
                        break;
                    }
                    int tmp = arr[r + 1][c];
                    for (int i = 0; i < X; i++) {
                        if (!inRange(r + 1 + i, c) || tmp != arr[r + 1 + i][c] || visit[r + 1 + i]) {
                            flag = true;
                            break;
                        }
                        visit[r + 1 + i] = true;
                    }
                }
            }
            if (!flag) {
                //					for (boolean i : visit) {
                //						System.out.print(i? 1: 0 + " ");
                //					}
                //					System.out.println(c + "이 만들 수 있음!");
                cnt++;
            }
        }


		System.out.print(cnt);
	}

	static boolean inRange(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < N;
	}
}
