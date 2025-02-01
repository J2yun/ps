import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int test_case = sc.nextInt();
        for (int t = 1; t <= test_case; t++) {
            int n = sc.nextInt();
            int num = sc.nextInt();
            int mask = (1 << n) - 1;
            if ((mask & num) == mask) {
                System.out.printf("#%d ON\n", t);
            } else {
                System.out.printf("#%d OFF\n", t);
            }
        }
    }
}
