import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] arr;
    static final int INF = 10000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(arr[i], INF);
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());
            arr[a][b] = cost;
        }

        for (int k = 0; k < N; k++) {
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    arr[r][c] = Math.min(arr[r][c], arr[r][k] + arr[k][c]);
                }
            }
        }

        int ans = INF;
        for (int i = 0; i < N; i++) {
            ans = Math.min(ans, arr[i][i]);
        }

        System.out.println(ans == INF ? -1 : ans);
    }
}
