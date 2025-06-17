import java.io.*;
import java.util.*;

public class Main {
    static Set<String> aeiou = Set.of("a", "e", "i", "o", "u");
    static List<String> arr = new ArrayList<>();
    static int L, C;
    static StringBuilder sb = new StringBuilder();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            arr.add(st.nextToken());
        }
        arr.sort((a, b) -> a.compareTo(b));

        back("", 0, -1);

        System.out.print(sb);
    }

    static void back(String s, int depth, int now) {
        if (depth == L) {
            if (isValidPw(s))
                sb.append(s).append("\n");
        }

        for (int i = now + 1; i < C; i++) {
            back(s + arr.get(i), depth + 1, i);
        }
    }

    static boolean isValidPw(String s) {
        int moCnt = 0, vaCnt = 0;
        for (String i : s.split("")) {
            if (aeiou.contains(i)) {
                moCnt++;
            } else {
                vaCnt++;
            }
            if (moCnt >= 1 && vaCnt >= 2) {
                return true;
            }
        }
        return false;
    }
}
