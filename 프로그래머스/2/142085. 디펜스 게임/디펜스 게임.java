import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> Integer.compare(b,a));
        
        int turn = enemy.length, t = 0;
        while (t < turn) {
            n -= enemy[t];
            pq.add(enemy[t]);
            if (n < 0) {
                while (k > 0 && n < 0) {
                    k--;
                    n += pq.remove();
                }
                if (n < 0) {
                    break;
                }
            } 
             
            // System.out.println("남은 체력: " + n + " 남은 무적권: " + k);
            t++;
        }
        
        
        return t;
    }
}