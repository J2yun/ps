import java.io.*;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Main {
    static int N, M, k;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int[][][] priority;
    static Smell[][] smell;
    static List<Shark> sharks = new ArrayList<>(); // {number, r, c}

    static class Smell {
        int sharkNum;
        int ttl;

        public Smell(int sharkNum, int ttl) {
            this.sharkNum = sharkNum;
            this.ttl = ttl;
        }

        // public String toString() {
        // return String.valueOf(ttl);
        // }
    }

    static class Shark {
        int num;
        int dir;
        int r;
        int c;

        public Shark(int num, int r, int c) {
            this.num = num;
            // this.dir = dir;
            this.r = r;
            this.c = c;
        }

        public String toString() {
            return String.format("[%d: (%d,%d/ dir:%d)]", num, r, c, dir);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        // arr = new int[N][N];

        // 냄새 입력
        smell = new Smell[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num > 0) {
                    sharks.add(new Shark(num, i, j));
                    smell[i][j] = new Smell(num, k);
                }
            }
        }

        // 상어 방향 입력
        int[] sDir = new int[M + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= M; i++) {
            sDir[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        for (Shark s : sharks) {
            s.dir = sDir[s.num];
        }

        // 상어 별 이동 우선 순위 입력
        priority = new int[M + 1][4][4];
        for (int i = 1; i <= M; i++) {
            for (int j = 0; j < 4; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < 4; k++) {
                    priority[i][j][k] = Integer.parseInt(st.nextToken()) - 1;
                }
            }
        }

        for (int time = 1; time <= 1000; time++) {
            turn();
            if (sharks.size() == 1) {
                System.out.println(time);
                return;
            }

            // System.out.println(sharks);
            // for (int i = 0; i < N; i++) {
            // for (int j = 0; j < N; j++) {
            // if (smell[i][j] != null) {
            // System.out.printf("%d ", smell[i][j].ttl);
            // } else {
            // System.out.printf("0 ");
            // }
            // }
            // System.out.println();
            // }

            // if (time == 2) {
            // break;
            // }
        }
        System.out.println(-1);
    }

    static boolean inRange(int r, int c) {
        return (0 <= r && r < N) && (0 <= c && c < N);
    }

    static void turn() {
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>(); // 좌표: 번호

        // 1. 모든 상어들은 자신의 우선순위에 따라 향기가 없는 다음 칸으로 이동
        for (Shark s : sharks) {
            int r = s.r;
            int c = s.c;

            boolean move = false;
            for (int i : priority[s.num][s.dir]) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (!inRange(nr, nc) || smell[nr][nc] != null) {
                    continue;
                }

                s.r = nr;
                s.c = nc;
                s.dir = i;
                int key = nr * 20 + nc;
                map.putIfAbsent(key, new ArrayList<>());
                map.get(key).add(s.num);
                move = true;
                break;
            }
            // 1 - 1. 만약 이동가능한 칸이 없는 경우 자신이 자신의 냄새가 있던 칸으로 이동
            if (!move) {
                for (int i : priority[s.num][s.dir]) {
                    int nr = r + dr[i];
                    int nc = c + dc[i];

                    // 이동 가능한 칸이 없다는 거는 벽이거나, 주변에 모두 향기가 있다는 뜻
                    if (inRange(nr, nc) && smell[nr][nc].sharkNum == s.num) {
                        s.r = nr;
                        s.c = nc;
                        s.dir = i;
                        int key = nr * 20 + c;
                        map.putIfAbsent(key, new ArrayList<>());
                        map.get(key).add(s.num);
                        break;
                    }
                }
            }
        }

        // 2. 모든 위치의 향기의 남은 시간이 1씩 감소
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (smell[r][c] != null) {
                    smell[r][c].ttl--;

                    if (smell[r][c].ttl == 0) {
                        smell[r][c] = null;
                    }
                }
            }
        }
        // System.out.println(map);
        // 3. 서로 먹힐 상어들은 먹히기
        List<Integer> remove = new ArrayList<>();
        for (int key : map.keySet()) {
            if (map.get(key).size() < 2) {
                continue;
            }
            int min = 401;
            for (int sNum : map.get(key)) {
                if (sNum < min) {
                    min = sNum;
                }
                remove.add(sNum);
            }
            remove.remove(remove.indexOf(min));
        }
        // System.out.println("remove: " + remove);

        Iterator<Shark> it = sharks.iterator();
        while (it.hasNext()) {
            Shark s = it.next();
            if (remove.contains(s.num)) {
                it.remove();
            }
        }

        // 4. 향기 뿌리기
        for (Shark s : sharks) {
            smell[s.r][s.c] = new Smell(s.num, k);
        }
    }
}
