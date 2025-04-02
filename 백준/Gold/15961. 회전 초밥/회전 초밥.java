import java.io.*;
import java.util.*;

public class Main {
	static int[] arr, contain;
	static int ans;

	public static void main(String[] args) throws Exception {
		int N = readInt();
		int d = readInt();
		int k = readInt();
		int c = readInt();

		arr = new int[N + k];
		contain = new int[d + 1];
		for (int i = 0; i < N; i++) {
			arr[i] = readInt();
		}
		for (int i = 0; i < k; i++) {
			arr[N + i] = arr[i];
		}

		int left = 0, right = k - 1, cntDiff = 0;
		for (int i = 0; i < k; i++) {
			int bap = arr[i];
			if (contain[bap] == 0) {
				cntDiff++;
			}
			contain[bap]++;
		}

		while (true) {
			if (cntDiff >= ans) {
				if (contain[c] > 0) {
					ans = cntDiff;
				} else {
					ans = cntDiff + 1;
				}
			}
			if (ans == k + 1) {
				break;
			}

			contain[arr[left]]--;
			if (contain[arr[left]] == 0) {
				cntDiff--;
			}
			left++;
			if (left == N) {
				break;
			}
			right++;
			if (contain[arr[right]] == 0) {
				cntDiff++;
			}
			contain[arr[right]]++;
		}

		System.out.println(ans);
	}

	static int readInt() throws Exception {
		int y, x = System.in.read() & 15;
		while ((y = System.in.read()) > 47) {
			x = (x << 3) + (x << 1) + (y & 15);
		}
		return x;
	}
}
