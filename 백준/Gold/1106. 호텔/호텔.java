import java.io.*;
import java.util.*;

public class Main {
    static int[] dp;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int K = Integer.parseInt(st.nextToken()) + 1000;
        int N = Integer.parseInt(st.nextToken());

        dp = new int[K + 1];
        arr = new int[2][K];
        Arrays.fill(dp, 1, K + 1, 1000000);

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[0][i] = Integer.parseInt(st.nextToken());
            arr[1][i] = Integer.parseInt(st.nextToken());
        }

        // System.out.println(Arrays.stream(dp).boxed().collect(Collectors.toList()));

        for (int r = 1; r <= K; r++) {
            for (int c = 0; c < N; c++) {
                if (arr[1][c] <= r) {
                    dp[r] = Math.min(dp[r - arr[1][c]] + arr[0][c], dp[r]);
                    // System.out.println((dp[r - arr[1][c]] + "+" + arr[0][c]) + " " + dp[r]);
                }
            }
            // System.out.println(dp[r]);
        }

        int ans = Integer.MAX_VALUE;
        for (int i = K - 1000; i < K; i++) {
            ans = Math.min(ans, dp[i]);
        }
        System.out.println(ans);
    }
}
