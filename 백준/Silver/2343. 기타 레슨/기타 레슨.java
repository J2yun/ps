import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(sol());
    }

    static int sol() {
        int left = 0, right = 1_000_000_001;
        for (int i : arr) {
            left = Math.max(i, left);
        }

        while (left <= right) {
            int mid = (left + right) / 2;

            int flag = 1, tmp = 0;;
            for (int i : arr) {
                tmp += i;
                if (tmp > mid) {
                    flag++;
                    tmp = i;
                }
            }

            if (M >= flag) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return left;

    }
}
