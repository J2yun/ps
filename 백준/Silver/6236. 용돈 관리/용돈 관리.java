import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];

        int left = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            if (arr[i] > left) {
                left = arr[i];
            }
        }

        int right = 1_000_000_000;

        while (left <= right) {
            int mid = (left + right) / 2;

            int cnt = 1;
            int balance = mid;
            for (int money : arr) {
                if (money > balance) {
                    cnt++;
                    balance = mid;
                }
                balance -= money;
            }

            if (cnt <= m) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(left);
    }
}
