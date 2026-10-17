import java.util.*;
class Solution {
    static private int [] root;

    public int solution(int n, int[][] costs) {
        int ans = 0;
        int edgeCnt = 0;

        root = new int [n];

        for (int i = 0; i < n; i++) {
            root[i] = i;
        }

        Arrays.sort(costs, (a, b) -> Integer.compare(a[2], b[2]));

        for (int [] cost : costs){
            int a = cost[0];
            int b = cost[1];
            int w = cost[2];

            if (find(a) != find(b)){
                union(a, b);
                ans += w;
                edgeCnt++;

                if (edgeCnt == n - 1) break;
            }
        }

        return ans;
    }

    static private int find(int a){
        if(root[a] == a){
            return a;
        }
        root[a] = find(root[a]);

        return root[a];
    }

    static private void union(int a, int b){
        a = find(a);
        b = find(b);

        if (a == b) return;

        root[a] = b;
    }
}