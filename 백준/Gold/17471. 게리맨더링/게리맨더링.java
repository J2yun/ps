import java.io.*;
import java.util.*;

public class Main {
    static int[] arr, parents;
    static int N, ans = Integer.MAX_VALUE;
    static LinkedList<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        parents = new int[N];
        graph = new LinkedList[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            graph[i] = new LinkedList<>();
            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            for (int j = 0; j < M; j++) {
                graph[i].add(Integer.parseInt(st.nextToken()) - 1);
            }
        }

        for (int i = 1; i < N / 2 + 1; i++) {
            makeGroup(0, 0, i, new LinkedList<>());
        }

        if (ans == Integer.MAX_VALUE)
            ans = -1;
        System.out.println(ans);
    }

    static void makeGroup(int n, int depth, int targetDepth, LinkedList<Integer> his) {
        if (depth == targetDepth) {
            LinkedList<Integer> tmp = new LinkedList<>();
            // 함수 호출하는데 g1을 순회하되, g2의 원소로는 못넘어감 순회 후 방문 개수와 g1 개수가 같아야 됨
            int g1 = 0, g2 = 0;
            for (int i = 0; i < N; i++) {
                if (his.contains(i)) {
                    g1 += arr[i];
                } else {
                    g2 += arr[i];
                    tmp.add(i);
                }
            }
            if (bfs(his, tmp) && bfs(tmp, his)) {
                int diff = Math.abs(g1 - g2);
                if (diff < ans) {
                    ans = diff;
                }
            }
            return;
        }

        for (int i = n; i < N; i++) {
            his.add(i);
            makeGroup(i + 1, depth + 1, targetDepth, his);
            his.removeLast();
        }
    }

    static boolean bfs(LinkedList<Integer> g1, LinkedList<Integer> g2) {
        HashSet<Integer> his = new HashSet<>();
        Queue<Integer> que = new LinkedList<>();
        que.add(g1.get(0));
        while (!que.isEmpty()) {
            int now = que.remove();
            his.add(now);

            for (int next : graph[now]) {
                if (!his.contains(next) && !g2.contains(next)) {
                    que.add(next);
                }
            }

        }

        if (g1.size() == his.size()) {
            return true;
        }
        return false;
    }
}
