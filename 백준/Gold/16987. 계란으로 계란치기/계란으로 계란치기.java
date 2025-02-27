import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
	static int N, ans = 0;
	static int[] durability, weight;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		durability = new int[N];
		weight = new int[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			durability[i] = Integer.parseInt(st.nextToken());
			weight[i] = Integer.parseInt(st.nextToken());

		}
		back(-1, 0);
		
		System.out.println(ans);
	}

	static void back(int now, int cnt) {
		now += 1;
//		System.out
//				.println("!!" + now + " " + cnt + " " + Arrays.stream(durability).boxed().collect(Collectors.toList()));
		if (cnt > ans) {
			ans = cnt;
		}
		
		if (now >= N || cnt == N) {
			return;
		}

		if (durability[now] <= 0) {
			back(now, cnt);
		}

		for (int next = 0; next < N; next++) {
			if (next == now)
				continue;
			if (durability[next] > 0 && durability[now] > 0) {
				durability[next] -= weight[now];
				durability[now] -= weight[next];
				int tmp = 0;
				if (durability[next] <= 0) {
					tmp++;
				}
				if (durability[now] <= 0) {
					tmp++;
				}

//				System.out.println(now + " " + next + " " + Arrays.stream(durability).boxed().collect(Collectors.toList()));
				back(now, cnt + tmp);
				durability[next] += weight[now];
				durability[now] += weight[next];
			}
		}

	}
}
