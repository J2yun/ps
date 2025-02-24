import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class Main {
    static int N, K, cntZero = 0, robotIdx = 1;
    static Conv putConv, outConv;
    static TreeMap<Integer, Conv> robots = new TreeMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        Conv start, end;

        Conv con = new Conv(Integer.parseInt(st.nextToken()));
        start = con;
        end = con;
        putConv = start;
        for (int i = 1; i < N; i++) {
            Conv c = new Conv(Integer.parseInt(st.nextToken()));
            end.right = c;
            c.left = end;

            end = c;
        }

        outConv = end;

        for (int i = 0; i < N; i++) {
            Conv c = new Conv(Integer.parseInt(st.nextToken()));
            end.right = c;
            c.left = end;

            end = c;
        }

        end.right = start;
        start.left = end;

        // Conv v = putConv;
        // for (int i = 0; i < 2 * N; i++) {
        // System.out.printf("(%d, %d) ", v.durability, v.robot);
        // v = v.right;
        // }
        // System.out.println();

        int turn;
        for (turn = 1;; turn++) {
            moveConv(); // 1. 컨베이어 밸트/로봇 이동
            moveRobot();
            putRobot();

            // Conv node = putConv;
            // for (int i = 0; i < 2 * N; i++) {
            // System.out.printf("(%d, %d) ", node.durability, node.robot);
            // node = node.right;
            // }
            // System.out.println();

            if (cntZero >= K)
                break;
        }
        System.out.println(turn);
    }

    static void moveConv() {
        putConv = putConv.left;
        outConv = outConv.left;

        if (outConv.robot > 0) {
            // System.out.println("outConv에 도달!");
            robots.remove(outConv.robot);
            outConv.robot = 0;
        }
    }

    static void moveRobot() {

        Iterator<Entry<Integer, Conv>> it = robots.entrySet().iterator();
        while (it.hasNext()) {
            Entry<Integer, Conv> entry = it.next();
            Conv r = entry.getValue();
            if (r.right.durability > 0 && r.right.robot == 0) {
                int tmp = r.robot;
                r.robot = 0;
                r.right.durability--;
                if (r.right.durability == 0) {
                    cntZero++;
                }

                if (r.right == outConv) {
                    // System.out.println("outConv에 도달!");
                    it.remove();
                    continue;
                }

                r.right.robot = tmp;
                robots.put(tmp, r.right);
            }
        }
    }

    static void putRobot() {
        if (putConv.durability > 0) {
            putConv.robot = robotIdx++;
            putConv.durability--;
            if (putConv.durability == 0) {
                // System.out.println("putRobot하면서 내구도 0");
                cntZero++;
            }
            robots.put(putConv.robot, putConv);
        }
    }

    static class Conv {
        int durability;
        int robot;
        Conv left;
        Conv right;

        public Conv(int d) {
            durability = d;
            robot = 0;
            left = null;
            right = null;
        }
    }
}
