import java.io.*;
import java.util.*;

public class Main {
    static Map<Integer, Integer> map = new HashMap<>();
    static int[] parents, arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        parents = new int[N + 1];
        arr = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            parents[i] = i;
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a, b);
        }

        for (int i = 1; i <= N; i++) {
            int tmp = find(i);
            map.putIfAbsent(tmp, Integer.MAX_VALUE);
            map.put(tmp, Math.min(arr[i], map.get(tmp)));
        }


        int cost = 0;
        for (int key : map.keySet()) {
            cost += map.get(key);
        }

        if (cost > K) {
            System.out.println("Oh no");
        } else {
            System.out.println(cost);
        }
    }

    static int find(int n) {
        if (n != parents[n]) {
            return parents[n] = find(parents[n]);
        }

        return n;
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a > b) {
            parents[a] = b;
        } else {
            parents[b] = a;
        }
    }
}
