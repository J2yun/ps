import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static List<Integer> arr = new ArrayList<>();
    static List<Integer> box = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr.add(Integer.parseInt(st.nextToken()));
        }
        arr.sort((a, b) -> Integer.compare(b, a));

        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            box.add(Integer.parseInt(st.nextToken()));
        }
        box.sort(((a, b) -> Integer.compare(b, a)));

        if (arr.get(0) < box.get(0)) {
            System.out.println(-1);
            return;
        }

        int time = 1;
        while (true) {
            int idx = 0;
            Iterator<Integer> iter = box.iterator();
            while (iter.hasNext()) {
                int tmp = iter.next();
                if (arr.get(idx) >= tmp) {
                    idx++;
                    iter.remove();
                }
                if (idx == N) {
                    break;
                }
            }
            if (box.isEmpty()) {
                break;
            }
            time++;
        }

        System.out.println(time);
    }
}
