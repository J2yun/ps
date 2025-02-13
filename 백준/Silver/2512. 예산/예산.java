import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        int sum = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            sum += arr[i];
        }
        int M = Integer.parseInt(br.readLine());

        int mid, flag, left = 0, right = M;

        if (sum <= M) {
            int ans = 0;
            for (int n : arr) {
                ans = n > ans ? n : ans;
            }
            System.out.println(ans);
            return;
        }

        while (left <= right) {
            mid = (left + right) / 2;

            flag = 0;
            for (int num : arr) {
                if (num <= mid) {
                    flag += num;
                } else {
                    flag += mid;
                }
            }

            if (flag > M) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(right);
    }
}
