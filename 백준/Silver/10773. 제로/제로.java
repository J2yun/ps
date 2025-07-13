import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        int[] stack = new int[1_000_001];
        int size = 0;


        int N = read();
        for (int i = 0; i < N; i++) {
            int a = read();
            if (a != 0) {
                stack[size++] = a;
            } else {
                size--;
            }
        }

        int ans = 0;
        for (int i = 0; i < size; i++) {
            ans += stack[i];
        }

        System.out.println(ans);
    }

    static int read() throws IOException {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}
