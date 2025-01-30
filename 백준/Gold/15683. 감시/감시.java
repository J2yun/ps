import java.io.*;
import java.util.*;

/*
 * CCTV는 최대 8개 64개마다 4개의 경우의 수 2^2가 8번 -> 2^16 = 시간 복잡도 충분 백트래킹으로 8개 모든 경우의 수 리스트 경우의 수 활용하여 사각지대 영역
 * 구하기 상 우 하 좌 cctv 별로 바라보는 방향 관리하는 배열 필요
 */

public class Main {
    static int[][] arr;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[][] dir = {{}, {0}, {0, 2}, {0, 1}, {0, 1, 2}, {0, 1, 2, 3}};
    static int[] dirLength = {0, 1, 2, 2, 3, 4};
    static int R, C, N, ans = Integer.MAX_VALUE;
    static List<int[]> cctvs = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new int[R][C];
        for (int r = 0; r < R; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < C; c++) {
                int tmp = Integer.parseInt(st.nextToken());
                if (tmp > 0 && tmp < 6) {
                    N += 1;
                    cctvs.add(new int[] {r, c, tmp});
                }
                arr[r][c] = tmp;
            }
        }
        back(new LinkedList<>());

        System.out.println(ans);
    }

    static boolean inRange(int r, int c) {
        return (0 <= r && r < R) && (0 <= c && c < C);
    }

    static void checkBlindSpot(LinkedList<Integer> his) {
        int[][] check = new int[R][C];

        for (int i = 0; i < N; i++) {
            // cctv 방향 체크해서 채우기, 벽 마주치면 정지
            int[] tmp = cctvs.get(i);
            int r = tmp[0];
            int c = tmp[1];
            int type = tmp[2];
            check[r][c] = 1;
            // CCTV i의 각도에 대한 방향 획득
            for (int d : getDir(type, his.get(i))) {
                int rr = r;
                int cc = c;
                while (inRange(rr, cc)) {
                    check[rr][cc] = 1;
                    if (arr[rr][cc] == 6) {
                        break;
                    }
                    rr = rr + dr[d];
                    cc = cc + dc[d];
                }
            }
        }
        // for (int[] nn : check) {
        // for (int n : nn) {
        // System.out.printf("%d ", n);
        // }
        // System.out.println();
        // }
        // 사각지대 개수 세서 반영
        int cnt = 0;
        for (int ii = 0; ii < R; ii++) {
            for (int jj = 0; jj < C; jj++) {
                if (check[ii][jj] == 0 && arr[ii][jj] != 6) {
                    cnt += 1;
                }
            }
        }
        if (cnt < ans) {
            ans = cnt;
        }
    }

    static void back(LinkedList<Integer> his) {
        if (his.size() == N) {
            checkBlindSpot(his);
            // System.out.println(his);
            return;
        }
        for (int i = 0; i < 4; i++) {
            his.add(i);
            back(his);
            his.removeLast();
        }
    }

    static int[] getDir(int cctv, int turn) { // cctv: CCTV 번호, turn: 회전 (0,1,2,3)
        if (cctv == 5) {
            return dir[5];
        }

        int[] ret = new int[dirLength[cctv]];
        for (int i = 0; i < dirLength[cctv]; i++) {
            ret[i] = (dir[cctv][i] + turn) % 4;
        }
        return ret;
    }
}
