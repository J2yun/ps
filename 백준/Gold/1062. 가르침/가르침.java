import java.io.*;
import java.util.*;

public class Main {
    static int[] words = new int[50];
    static int N, K, ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());


        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        if (K < 5) {
            System.out.println(0);
            return;
        }

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (char c : s.toCharArray()) {
                words[i] = words[i] | (1 << (c - 'a'));
            }
            // System.out.println(Integer.toBinaryString(words[i]));
        }

        back(0, -1, 0);

        System.out.println(ans);
    }

    static void back(int depth, int now, int val) {
        if (depth == K) {
            // System.out.println(Integer.toBinaryString(val));
            int cnt = ableToLearn(val);
            ans = Math.max(cnt, ans);
            return;
        }

        for (int i = now + 1; i < 26; i++) {
            back(depth + 1, i, val | (1 << i));
        }
    }

    static int ableToLearn(int now) {

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            boolean flag = true;
            for (int j = 0; j < 26; j++) {
                int know = now & (1 << j);
                int target = words[i] & (1 << j);
                if (know == 0 && target > 0) {
                    flag = false;
                    break;
                }
            }
            if (flag)
                cnt++;
        }

        return cnt;
    }
}
