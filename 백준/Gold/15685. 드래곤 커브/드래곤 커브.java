import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    static int N;
    static boolean[][] arr = new boolean[101][101];

    public static void main(String[] args) throws IOException {
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, -1, -0, 1};

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            makeDcurve(x, y, x + dx[d], y + dy[d], g);
        }

        int ans = 0;
        for (int r = 0; r < 100; r++) {
            for (int c = 0; c < 100; c++) {
                if (arr[r][c] && arr[r + 1][c] && arr[r][c + 1] && arr[r + 1][c + 1]) {
                    ans++;
                }
            }
        }
        System.out.println(ans);
    }

    static void makeDcurve(int x, int y, int a, int b, int g) {
        HashSet<List<Integer>> set = new HashSet<>();
        set.add(Arrays.asList(x, y));
        set.add(Arrays.asList(a, b));
        int aa = 0, bb = 0;

        for (int i = 0; i < g; i++) {
            for (List<Integer> tmp : set.stream().collect(Collectors.toList())) {
                // tmp == (x,y)면 나중에 a,b로 써야됨
                int xx = tmp.get(0);
                int yy = tmp.get(1);
                List<Integer> next = getTurn(xx, yy, a, b);
                if (xx == x && yy == y) {
                    aa = next.get(0);
                    bb = next.get(1);
                }
                set.add(next);
            }
            a = aa;
            b = bb;
            // System.out.println(set);
        }
        for (List<Integer> tmp : set.stream().collect(Collectors.toList())) {
            int c = tmp.get(0);
            int r = tmp.get(1);
            if (inRange(c, r)) {
                arr[r][c] = true;
            }
        }

    }

    static boolean inRange(int x, int y) {
        return (0 <= x && x <= 100) && (0 <= y && y <= 100);
    }

    static List<Integer> getTurn(int x, int y, int a, int b) {
        return Arrays.asList(a + b - y, b - a + x);
    }
}
