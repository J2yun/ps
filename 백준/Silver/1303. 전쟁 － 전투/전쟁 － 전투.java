import java.io.*;
import java.util.*;

public class Main {
    static int R, C;
    static int[][] arr;
    static boolean[][] visit;
    static Queue<int[]> que;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        int[] ans = new int[2];
        arr = new int[R][C];
        visit = new boolean[R][C];

        for (int r = 0; r < R; r++) {
            String s = br.readLine();
            for (int c = 0; c < C; c++) {
                arr[r][c] = s.charAt(c) == 'W' ? 0 : 1;
            }
        }

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (!visit[r][c])
                    ans[arr[r][c]] += bfs(r, c, arr[r][c]);
            }
        }
        System.out.println(ans[0] + " " + ans[1]);
    }



    static boolean inRange(int r, int c) {
        return 0 <= r && r < R && 0 <= c && c < C;
    }

    static int bfs(int r, int c, int team) {
        int cnt = 1;
        que = new LinkedList<>();
        que.add(new int[] {r, c});
        visit[r][c] = true;

        while (!que.isEmpty()) {
            int[] tmp = que.remove();
            r = tmp[0];
            c = tmp[1];

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (inRange(nr, nc) && !visit[nr][nc] && arr[nr][nc] == team) {
                    que.add(new int[] {nr, nc});
                    visit[nr][nc] = true;
                    cnt++;
                }
            }
        }


        return cnt * cnt;
    }
}
