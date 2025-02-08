import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class Main {
    static int R, C, K, rlen = 3, clen = 3;
    static int[][] arr = new int[100][100];
    static HashMap<Integer, Integer> map = new HashMap<>();
    static List<int[]> tmp = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken()) - 1;
        C = Integer.parseInt(st.nextToken()) - 1;
        K = Integer.parseInt(st.nextToken());

        for (int r = 0; r < 3; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < 3; c++) {
                arr[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        if (arr[R][C] == K) {
            System.out.println(0);
        } else {
            System.out.println(solution());
        }
    }

    static int solution() {
        for (int sec = 1; sec <= 100; sec++) {
            if (rlen >= clen) {
                sortR();
            } else {
                sortC();
            }
            // System.out.println(rlen + " " + clen);
            // for (int r = 0; r < rlen; r++) {
            // for (int c = 0; c < clen; c++) {
            // System.out.printf("%d ", arr[r][c]);
            // }
            // System.out.println();
            // }

            if (arr[R][C] == K) {
                return sec;
            }

            // if (sec == 2) {
            // break;
            // }
        }

        return -1;
    }

    static void sortR() {
        int tmpCLen = 0;
        for (int r = 0; r < rlen; r++) {
            map.clear();
            tmp.clear();
            for (int c = 0; c < clen; c++) {
                int key = arr[r][c];
                map.putIfAbsent(key, 0);
                map.merge(key, 1, (a, b) -> a + b);
                arr[r][c] = 0;
            }
            calculate();
            // System.out.println(map);
            int size = tmp.size();
            for (int i = 0; i < size; i++) {
                int[] res = tmp.get(i);
                arr[r][i * 2] = res[0];
                arr[r][i * 2 + 1] = res[1];
                if (i == 49) {
                    break;
                }
            }

            if (tmpCLen < size * 2) {
                tmpCLen = size * 2;
            }
        }

        clen = tmpCLen;
    }

    static void sortC() {
        int tmpRLen = 0;
        for (int c = 0; c < clen; c++) {
            map.clear();
            tmp.clear();
            for (int r = 0; r < rlen; r++) {
                int key = arr[r][c];
                map.putIfAbsent(key, 0);
                map.merge(key, 1, (a, b) -> a + b);
                arr[r][c] = 0;
            }
            calculate();
            // System.out.println(map);
            int size = tmp.size();
            for (int i = 0; i < size; i++) {
                int[] res = tmp.get(i);
                arr[i * 2][c] = res[0];
                arr[i * 2 + 1][c] = res[1];
                if (i == 49) {
                    break;
                }
            }

            if (tmpRLen < size * 2) {
                tmpRLen = size * 2;
            }
        }

        rlen = tmpRLen;

    }

    static void calculate() {
        for (Entry<Integer, Integer> e : map.entrySet()) {
            if (e.getKey() == 0) {
                continue;
            }
            tmp.add(new int[] {e.getKey(), e.getValue()});
        }
        Collections.sort(tmp, (a, b) -> {
            if (a[1] == b[1]) {
                return Integer.compare(a[0], b[0]);
            } else {
                return Integer.compare(a[1], b[1]);
            }
        });
    }
}
