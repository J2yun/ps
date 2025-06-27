import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] arr, cost;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static Queue<int[]> que;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = 1;
        while (true) {
            N = Integer.parseInt(br.readLine());
            if (N == 0)
                break;

            arr = new int[N][N];
            cost = new int[N][N];
            for (int r = 0; r < N; r++) {
                Arrays.fill(cost[r], Integer.MAX_VALUE);
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < N; c++) {
                    arr[r][c] = Integer.parseInt(st.nextToken());
                }
            }

            sb.append("Problem ").append(T++).append(": ").append(sol()).append("\n");
        }

        System.out.print(sb);
    }

    static int sol() {
        que = new LinkedList<>();
        cost[0][0] = arr[0][0];
        que.add(new int[] {0, 0});

        while (!que.isEmpty()) {
            int[] tmp = que.remove();
            int r = tmp[0], c = tmp[1];
            int now = cost[r][c];

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (inRange(nr, nc)) {
                    if (now + arr[nr][nc] < cost[nr][nc]) {
                        cost[nr][nc] = now + arr[nr][nc];
                        que.add(new int[] {nr, nc});
                    }
                }
            }
        }

        return cost[N - 1][N - 1];
    }

    static boolean inRange(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N;
    }
}
