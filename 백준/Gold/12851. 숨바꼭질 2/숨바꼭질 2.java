import java.util.*;

public class Main {
    public static void main(String[] args) {
        final int MAX = 10_0001;
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();

        int min = Integer.MAX_VALUE, cnt = 0;
        int[] visit = new int[MAX];

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        pq.add(new int[] {0, N});
        while (!pq.isEmpty()) {
            int[] tmp = pq.remove();
            int now = tmp[1];
            int time = tmp[0];
            // System.out.println(now + " " + time);

            if (min < time) {
                // System.out.println(pq);/
                // System.out.println(time);
                break;
            }

            if (now == K) {
                min = time;
                cnt++;
                continue;
            }

            int arr[] = {now - 1, now + 1, now * 2};
            for (int n : arr) {
                if (0 > n || n > MAX - 1) {
                    continue;
                }
                if (visit[n] == 0 || visit[n] == time + 1) {
                    visit[n] = time + 1;
                    pq.add(new int[] {time + 1, n});
                }
            }


        }
        System.out.printf("%d\n%d\n", min, cnt);
    }
}
