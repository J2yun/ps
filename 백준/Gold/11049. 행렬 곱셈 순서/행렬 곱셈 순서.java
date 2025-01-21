import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        final int MAX = Integer.MAX_VALUE;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][2];
        int[][] dp = new int[N][N];

        for (int i = 0; i < N; i++) {
            String[] tmp = br.readLine().split(" ");
            arr[i][0] = Integer.parseInt(tmp[0]);
            arr[i][1] = Integer.parseInt(tmp[1]);
        }

        // DP
        for (int space = 1; space < N; space++) {
            for (int start = 0; start < N - space; start++) {
                dp[start][start + space] = MAX;
                for (int mid = start; mid < start + space; mid++) {
                    dp[start][start + space] = Math.min(dp[start][start + space],
                            dp[start][mid] + dp[mid + 1][start + space]
                                    + arr[start][0] * arr[mid][1] * arr[start + space][1]);
                }
            }
        }

        System.out.println(dp[0][N - 1]);

    }
}
