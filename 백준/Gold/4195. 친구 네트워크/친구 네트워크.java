import java.io.*;
import java.util.*;

public class Main {
    static int[] parents, friends;
    static Map<String, Integer> nameToNum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        parents = new int[200000];
        friends = new int[200000];

        int T = Integer.parseInt(br.readLine());
        int cnt = 0;
        for (int tc = 0; tc < T; tc++) {
            nameToNum = new HashMap<>();
            int N = Integer.parseInt(br.readLine());
            for (int n = 0; n < N; n++) {
                st = new StringTokenizer(br.readLine());
                String sA = st.nextToken(), sB = st.nextToken();
                boolean isNewA = false, isNewB = false;
                if (!nameToNum.containsKey(sA)) {
                    parents[cnt] = cnt;
                    friends[cnt] = 1;
                    nameToNum.put(sA, cnt++);
                    isNewA = true;
                }
                if (!nameToNum.containsKey(sB)) {
                    parents[cnt] = cnt;
                    friends[cnt] = 1;
                    nameToNum.put(sB, cnt++);
                    isNewB = true;
                }

                int a = nameToNum.get(sA);
                int b = nameToNum.get(sB);

                if (find(a) == find(b)) {
                    union(a, b);
                    if (isNewA || isNewB)
                        friends[find(a)]++;
                } else {
                    int tmp = friends[find(a)] + friends[find(b)];
                    union(a, b);
                    friends[find(b)] = tmp;
                }
                sb.append(friends[find(a)]).append("\n");
            }
        }
        System.out.print(sb);
    }

    static int find(int n) {
        if (n != parents[n]) {
            parents[n] = find(parents[n]);
        }

        return parents[n];
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        parents[a] = b;
    }
}
