import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static long[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new long[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Long.parseLong(st.nextToken());
            }
        }

        Queue<Node> que = new LinkedList<>();
        que.add(new Node(arr, 0));
        long ans = 0;

        while (!que.isEmpty()) {
            Node now = que.remove();
            long max = now.getMax();
            if (max > ans) {
                ans = max;
            }

            if (now.depth == 5) {
                continue;
            }

            long[][] left = mv_left(now.arr);
            long[][] right = mv_right(now.arr);
            long[][] up = mv_up(now.arr);
            long[][] down = mv_down(now.arr);
            if (!now.arrEquals(left)) {
                que.add(new Node(left, now.depth + 1));
            }
            if (!now.arrEquals(right)) {
                que.add(new Node(right, now.depth + 1));
            }
            if (!now.arrEquals(up)) {
                que.add(new Node(up, now.depth + 1));
            }
            if (!now.arrEquals(down)) {
                que.add(new Node(down, now.depth + 1));
            }
        }

        System.out.println(ans);

        // print(arr);
        // long[][] left = mv_left(arr);
        // print(left);
        // long[][] right = mv_right(arr);
        // print(right);
        // long[][] up = mv_up(arr);
        // print(up);

        // List<Long> arr = Arrays.asList(128l, 128l);
        // System.out.println(combine(arr));

        // System.out.println(arrEquals(arr, up));

    }

    static class Node {
        long[][] arr;
        int depth;

        public Node(long[][] arr, int depth) {
            this.arr = arr;
            this.depth = depth;
        }

        long getMax() {
            long ret = 0;
            for (long[] tmp : arr) {
                for (long n : tmp) {
                    if (ret < n) {
                        ret = n;
                    }
                }
            }
            return ret;
        }

        boolean arrEquals(long[][] a) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (a[i][j] != this.arr[i][j]) {
                        return false;
                    }
                }
            }
            return true;
        }
    }

    static void print(long[][] arr) {
        System.out.println("print arr...");
        for (long[] i : arr) {
            for (long j : i) {
                System.out.printf("%d ", j);
            }
            System.out.println();
        }
    }

    static long[][] mv_right(long[][] arr) {
        long[][] moved = new long[N][N];
        // 행 별로 옮기기
        for (int r = 0; r < N; r++) {
            // r번째 행 목록 구해서
            List<Long> l = new ArrayList<>();
            for (int i = N - 1; i >= 0; i--) {
                if (arr[r][i] > 0) {
                    l.add(arr[r][i]);
                }
            }
            // r번째 행 합치기 (모듈화 ㄱㄴ)
            List<Long> comb = combine(l);
            for (int i = 0; i < comb.size(); i++) {
                moved[r][N - 1 - i] = comb.get(i);
            }
        }

        return moved;
    }

    static long[][] mv_left(long[][] arr) {
        long[][] moved = new long[N][N];
        // 행 별로 옮기기
        for (int r = 0; r < N; r++) {
            // r번째 행 목록 구해서
            List<Long> l = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                if (arr[r][i] > 0) {
                    l.add(arr[r][i]);
                }
            }
            // r번째 행 합치기 (모듈화 ㄱㄴ)
            List<Long> comb = combine(l);
            for (int i = 0; i < comb.size(); i++) {
                moved[r][i] = comb.get(i);
            }
        }

        return moved;
    }

    static long[][] mv_up(long[][] arr) {
        long[][] moved = new long[N][N];
        // 행 별로 옮기기
        for (int c = 0; c < N; c++) {
            // r번째 행 목록 구해서
            List<Long> l = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                if (arr[i][c] > 0) {
                    l.add(arr[i][c]);
                }
            }
            // r번째 행 합치기 (모듈화 ㄱㄴ)
            List<Long> comb = combine(l);
            for (int i = 0; i < comb.size(); i++) {
                moved[i][c] = comb.get(i);
            }
        }

        return moved;
    }

    static long[][] mv_down(long[][] arr) {
        long[][] moved = new long[N][N];
        // 행 별로 옮기기
        for (int c = 0; c < N; c++) {
            // r번째 행 목록 구해서
            List<Long> l = new ArrayList<>();
            for (int i = N - 1; i >= 0; i--) {
                if (arr[i][c] > 0) {
                    l.add(arr[i][c]);
                }
            }
            // r번째 행 합치기 (모듈화 ㄱㄴ)
            List<Long> comb = combine(l);
            for (int i = 0; i < comb.size(); i++) {
                moved[N - 1 - i][c] = comb.get(i);
            }
        }

        return moved;
    }

    static List<Long> combine(List<Long> src) {
        List<Long> ret = new ArrayList<>();
        for (int i = 0; i < src.size(); i++) {
            if (i == src.size() - 1) { // 마지막놈이 합쳐지지 않았을 경우
                ret.add(src.get(i));
                break;
            }

            if (src.get(i).equals(src.get(i + 1))) {
                ret.add(2 * src.get(i));
                i += 1;
            } else {
                ret.add(src.get(i));
            }
        }
        return ret;
    }


}
