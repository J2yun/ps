import java.io.*;
import java.util.*;

public class Main {
    static final long MOD = 1_000_000_007L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        long ans = 0;

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            long b = Long.parseLong(st.nextToken());
            long a = Long.parseLong(st.nextToken());

            // 기약분수로 만들기
            long gcd = gcd(a, b);
            a /= gcd;
            b /= gcd;

            ans += modular(a, b);
        }

        System.out.println(ans % MOD);
    }

    static long modular(long a, long b) {
        return a * pow(b, MOD - 2) % MOD;
    }

    static long pow(long num, long k) {
        if (k == 1)
            return num;
        long tmp = pow(num, k / 2) % MOD;
        if (k % 2 == 0) {
            return (tmp * tmp) % MOD;
        } else {
            return (((tmp * tmp) % MOD) * num) % MOD;
        }
    }

    static long gcd(long a, long b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }
}
