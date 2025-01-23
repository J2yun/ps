import java.io.*;
// import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < N; i++) {
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (arr[r][i] == 1 && arr[i][c] == 1)
                        arr[r][c] = 1;
                }
            }
        }

        for (int[] tmp : arr) {
            for (int n : tmp) {
                sb.append(n).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
