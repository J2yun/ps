import java.io.*;
import java.util.*;

public class Main {
    static int R, C, er, ec;
    static int[][] arr;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader brr = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(brr.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new int[R][C];
        int rr = 0, rc = 0, br = 0, bc = 0;

        for (int i = 0; i < R; i++) {
            String tmp = brr.readLine();
            for (int j = 0; j < C; j++) {
                char c = tmp.charAt(j);
                if (c == 'R') {
                    rr = i;
                    rc = j;
                } else if (c == 'B') {
                    br = i;
                    bc = j;
                } else if (c == 'O') {
                    er = i;
                    ec = j;
                } else if (c == '#') {
                    arr[i][j] = -1;
                }
            }
        }
        System.out.println(sol(rr, rc, br, bc));
    }

    static int sol(int srr, int src, int sbr, int sbc) {
        Queue<int[]> que = new LinkedList<>();
        que.add(new int[] {srr, src, sbr, sbc, 0});

        while (!que.isEmpty()) {
            // 1. red blue 상하좌우 상태 저장
            // 2. 4방향으로 최대한 이동
            // 3. 같은 위치일 경우 조정
            int[] tmp = que.remove();
            int xrr = tmp[0];
            int xrc = tmp[1];
            int xbr = tmp[2];
            int xbc = tmp[3];
            int cnt = tmp[4];

            if (cnt == 11) {
                break;
            }

            int red_down = xrr - xbr; // 0보다 크면 red가 아래
            int red_left = xrc - xbc; // 0보다 크면 red가 왼쪽


            for (int i = 0; i < 4; i++) {
                boolean rflag = false, bflag = false;
                int rr = xrr, rc = xrc;
                int br = xbr, bc = xbc;
                // red 이동
                while (true) {
                    if (rr == er && rc == ec) {
                        rflag = true;
                        break;
                    }

                    int nrr = rr + dr[i];
                    int nrc = rc + dc[i];
                    if (arr[nrr][nrc] == -1) {
                        break;
                    }
                    rr = nrr;
                    rc = nrc;
                    // System.out.print(rr + "," + rc + "/");
                }
                // System.out.println();

                // blue 이동
                while (true) {
                    if (br == er && bc == ec) {
                        bflag = true;
                        break;
                    }

                    int nbr = br + dr[i];
                    int nbc = bc + dc[i];
                    if (arr[nbr][nbc] == -1) {
                        break;
                    }
                    br = nbr;
                    bc = nbc;
                    // System.out.print(br + "," + bc + "/");
                }
                // System.out.println("rflag: " + rflag + " bflag: " + bflag);

                if (rflag && !bflag) {
                    return cnt + 1;
                } else if (rflag || bflag) {
                    continue;
                }

                // 겹쳐있을 경우
                if ((rr == br) && (rc == bc)) {
                    // 위아래 이동한 경우
                    if (red_left == 0) {
                        if (dr[i] * red_down > 0) {
                            br = br - dr[i];
                        } else {
                            rr = rr - dr[i];
                        }
                    }

                    // 좌우 이동한 경우
                    if (red_down == 0) {
                        if (dc[i] * red_left > 0) {
                            bc = bc - dc[i];

                            // System.out.println("!");
                        } else {

                            // System.out.println(dc[i] + "!!" + red_left);
                            rc = rc - dc[i];

                        }
                    }
                }

                if (rr == xrr && rc == xrc && br == xbr && bc == xbc) {
                    continue;
                }
                // System.out.println("red(" + rr + "," + rc + ")" + " blue(" + br + "," + bc +
                // ")");
                if (cnt < 9) {
                    que.add(new int[] {rr, rc, br, bc, cnt + 1});
                }
            }
        }

        return -1;
    }
}
