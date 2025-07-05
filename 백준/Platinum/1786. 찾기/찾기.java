import java.util.*;

public class Main {
    static int[] lps;
    static List<Integer> ans = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        char[] T = sc.nextLine().toCharArray();
        char[] P = sc.nextLine().toCharArray();

        int pLen = P.length;
        lps = new int[pLen];
        int idx = 0; // idx는 비교할 접두사/접미사의 길이를 의미
        // i를 기준으로 idx 길이 만큼의 접두사/접미사를 비교한다.
        for (int i = 1; i < pLen; i++) {
            // 1. 만약 비교한 값이 값으면 lps[]에 반영시키고
            // 2. 앞으로 비교할동일 접두사/접미사의 길이를 늘려준다.
            if (P[i] == P[idx]) {
                lps[i] = ++idx;
                // 다를 경우
            } else {
                // 만약 이전에 비교했던 접두사/접미사 길이기 1보다 큰 경우
                if (idx != 0) {
                    // 이전 정보를 사용한다는데 이해가 잘 안됨
                    idx = lps[idx - 1];
                    i--;
                }
            }
        }

        idx = 0; // 마찬가지로 겹치는 접두사/접미사 길이
        for (int i = 0; i < T.length; i++) {
            // 지금까지 일치했던 접두사/접미사가 있고
            // 이번에 틀려버림, 비교할 P의 위치를 위한 lps[idx -1]
            // while 문을 통해 효율적으로 찾음
            while (idx > 0 && T[i] != P[idx]) {
                idx = lps[idx - 1];
            }

            // 접두사/접미사가 같은 경우
            if (T[i] == P[idx]) {
                // 길이 꽉채웠으면 일치한거
                if (idx == pLen - 1) {
                    ans.add(i - idx + 1);
                    idx = lps[idx];
                    // 길이가 달랐으면 더 비교해야됨 -> idx 증가
                } else {
                    idx++;
                }
            }
        }

        sb.append(ans.size()).append("\n");
        for (int i : ans) {
            sb.append(i).append("\n");
        }
        System.out.print(sb);
        sc.close();
    }
}
