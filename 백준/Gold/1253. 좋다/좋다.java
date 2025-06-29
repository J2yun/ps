import java.io.*;
import java.util.*;

public class Main {
    static long[] arr;
    static boolean[] good;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int ans = 0;
        arr = new long[N];
        good = new boolean[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            arr[i] = Long.parseLong(st.nextToken());
        Arrays.sort(arr);

        if (N <= 2) {
            System.out.println(ans);
            return;
        }
        for (int i = 0; i < N; i++) {
            int left = 0, right = N - 1;

            while (left < right) {
                if (left == i) {
                    left++;
                    continue;
                } else if (right == i) {
                    right--;
                    continue;
                }

                if (arr[left] + arr[right] == arr[i]) {
                    good[i] = true;
                    break;
                } else if (arr[left] + arr[right] > arr[i]) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        for (boolean flag : good) {
            if (flag)
                ans++;
        }

        System.out.println(ans);
    }
}
