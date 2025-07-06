import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        List<Point> arr = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            arr.add(new Point(x, y));
        }

        arr.sort((a, b) -> {
            if (a.y == b.y) {
                return Integer.compare(a.x, b.x);
            } else {
                return Integer.compare(a.y, b.y);
            }
        });

        for (Point p : arr) {
            sb.append(p.x).append(" ").append(p.y).append("\n");
        }

        System.out.print(sb);
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
