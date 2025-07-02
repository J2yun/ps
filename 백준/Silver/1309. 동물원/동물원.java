import java.io.*;
import java.util.*;

public class Main {
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        arr = new int[2][N];
        arr[0][0] = 1;
        arr[1][0] = 2;
        for (int i = 1; i < N; i++) {
            arr[0][i] = (arr[0][i - 1] + arr[1][i - 1]) % 9901;
            arr[1][i] = (2 * arr[0][i - 1] + arr[1][i - 1]) % 9901;
        }

        System.out.println((arr[0][N - 1] + arr[1][N - 1]) % 9901);
    }
}
