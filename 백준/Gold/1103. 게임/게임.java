import java.io.*;
import java.util.*;

public class Main {
    static int R, C, ans;
    static int[][] arr, mem;
    static boolean[][] visit;
    static boolean haveCicle = false;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static final int HOLE = 'H' - '1' + 1;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new int[R][C];
        mem = new int[R][C];
        visit = new boolean[R][C];

        for (int r = 0; r < R; r++) {
            String s = br.readLine();
            for (int c = 0; c < C; c++) {
                arr[r][c] = s.charAt(c) - '1' + 1;
            }
        }

        visit[0][0] = true;
        dfs(0, 0, 1);

        // for (int[] tmp : arr) {
        // System.out.println(Arrays.stream(tmp).boxed().collect(Collectors.toList()));
        // }
        System.out.println(haveCicle ? -1 : ans);

    }

    static void dfs(int r, int c, int depth) {
        if (haveCicle)
            return;
        ans = Math.max(depth, ans);

        mem[r][c] = depth;

        // System.out.println(r + " " + c);
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i] * arr[r][c];
            int nc = c + dc[i] * arr[r][c];
            if (!inRange(nr, nc) || arr[nr][nc] == HOLE) {
                continue;
            }

            if (visit[nr][nc]) {
                haveCicle = true;
                return;
            }
            if (mem[nr][nc] > depth) {
                // System.out.println("!");
                // ans = Math.max(depth + mem[nr][nc], ans);
                // mem[r][c] = Math.max(depth + mem[nr][nc]);
                continue;
            }

            visit[nr][nc] = true;
            dfs(nr, nc, depth + 1);
            visit[nr][nc] = false;
            // mem[r][c] = Math.max(mem[r][c], mem[nr][nc] + 1);
        }
        ans = Math.max(ans, mem[r][c]);
        // System.out.println();
    }

    static boolean inRange(int r, int c) {
        return 0 <= r && r < R && 0 <= c && c < C;
    }
}
