import java.io.*;
import java.util.*;

public class Main {
    static LinkedList<Integer> a = new LinkedList<>();
    static LinkedList<Integer> b = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;


        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            a.add(Integer.parseInt(st.nextToken()));
        }

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            b.add(Integer.parseInt(st.nextToken()));
        }

        List<Integer> ans = sol(a, b, new ArrayList<>());

        sb.append(ans.size()).append("\n");
        for (int i : ans) {
            sb.append(i).append(" ");
        }

        System.out.println(sb);
    }

    public static List<Integer> sol(LinkedList<Integer> a, LinkedList<Integer> b,
            List<Integer> ans) {

        if (a.isEmpty() || b.isEmpty()) {
            return ans;
        }
        int amax = Collections.max(a);
        int bmax = Collections.max(b);

        int a_idx = a.indexOf(amax);
        int b_idx = b.indexOf(bmax);

        if (amax == bmax) {
            ans.add(amax);
            for (int i = 0; i <= a_idx; i++) {
                a.removeFirst();
            }
            for (int i = 0; i <= b_idx; i++) {
                b.removeFirst();
            }
            return sol(a, b, ans);
        } else if (amax > bmax) {
            a.remove(a_idx);
            return sol(a, b, ans);
        } else {
            b.remove(b_idx);
            return sol(a, b, ans);
        }

    }
}
