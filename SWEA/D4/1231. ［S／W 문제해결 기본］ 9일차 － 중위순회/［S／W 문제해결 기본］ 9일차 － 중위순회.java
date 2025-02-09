import java.io.*;
import java.util.*;

public class Solution {
    static int N;
    static Node[] nodes;
    static StringBuilder ans = new StringBuilder();
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int tc = 1; tc <= 10; tc++) {
            N = Integer.parseInt(br.readLine());
            nodes = new Node[N + 1];
            for (int i = 0; i <= N; i++) {
                nodes[i] = new Node();
            }

            // Make Tree
            for (int n = 1; n <= N; n++) {
                String[] tmp = br.readLine().split(" ");
                nodes[n].val = tmp[1];
                if (tmp.length >= 4) {
                    nodes[n].right = nodes[Integer.parseInt(tmp[3])];
                }
                if (tmp.length >= 3) {
                    nodes[n].left = nodes[Integer.parseInt(tmp[2])];
                }
            }

            sb = new StringBuilder();
            inorder(nodes[1]);
            ans.append("#").append(tc).append(" ").append(sb.toString()).append("\n");
        }

        System.out.print(ans);
    }

    static void inorder(Node node) {
        if (node.left != null) {
            inorder(node.left);
        }
        sb.append(node.val);
        // System.out.println(node.val);

        if (node.right != null) {
            inorder(node.right);
        }
    }

    static class Node {
        String val;
        Node left = null;
        Node right = null;
    }
}
