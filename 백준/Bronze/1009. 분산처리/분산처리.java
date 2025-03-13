import java.io.*;
import java.util.*;

public class Main {
	static ArrayList<Integer> arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			arr = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			int period = getPeriod(a, b);
			int ret = arr.get((b-1) % period);
			sb.append(ret).append("\n");
			
		}

		System.out.print(sb);
	}
	
	static int getPeriod(int a, int b) {
		int start = a % 10;
		if (start == 0) {
			arr.add(10);
		} else {
			arr.add(start);
		}
		
		int now = a;
		while (true) {
			now = (now * a) % 10;
			if (now == start) {
				break;
			} else {
				arr.add(now);
			}
		}
		
		return arr.size();
	}
}
