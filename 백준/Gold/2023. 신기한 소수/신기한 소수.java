import java.io.*;
import java.util.*;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int[] arr = {1, 2, 3, 5, 7, 9 };

	public static void main(String[] args) throws IOException {
		// 에라토스테네스의 체
//		for (int i = 2; i < 100_000_000; i++) {
//			if (notSosu[i]) {
//				continue;
//			}
//			int s = i + i;
//			while (s < 100_000_000) {
//				notSosu[s] = true;
//				s += i;
//			}
//		}
//		notSosu[1] = true;

		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();

		back(2, 1);
		back(3, 1);
		back(5, 1);
		back(7, 1);
		System.out.println(sb);
		sc.close();
	}

	static boolean isSosu(int n) {
		for (int i = 2; i < n / 2 + 1; i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}

	static void back(int now, int depth) {
		if (depth == N) {
//			boolean flag = true;
			int val = now;
			for (int i = 0; i < 4; i++) {
				if (!isSosu(val)) {
//					flag = false;
					return;

				}
				val = val / 10;
			}
//			if (flag) {
			sb.append(now).append("\n");
//			}
			return;
		}

		for (int i = 0; i < 6; i++) {
			if (!isSosu(now)) {
				continue;
			}
			back(10 * now + arr[i], depth + 1);
		}
	}
}
