import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int five = N / 5;
		N = N % 5;

		if (N % 3 == 0) {
			System.out.println(five + N / 3);
			return;
		}

		while (five > 0) {
			five--;
			N += 5;
			
			if (N % 3 == 0) {
				System.out.println(five + N / 3);
				return;
			}
		}

		System.out.println(-1);
	}
}