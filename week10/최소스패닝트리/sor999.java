// 크루스칼 - O(E log E)
import java.io.*;
import java.util.*;

public class Main {
    static private int [] root;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        root = new int [v + 1];

        for(int i = 1; i <= v; i++){
            root[i] = i;
        }

        List<int []> edges = new ArrayList<>();

        for(int i = 0; i < e; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            edges.add(new int [] {a, b, c});
        }

        edges.sort((a, b) -> Integer.compare(a[2], b[2]));

        int ans = 0;
        int edgeCnt = 0;

        for(int [] edge : edges){
            int a = edge[0];
            int b = edge[1];
            int w = edge[2];


            if(find(a) != find(b)){
                union(a, b);
                ans += w;
                edgeCnt++;

                if (edgeCnt == v - 1) break;
            }

        }

        System.out.println(ans);
    }

    static private int find(int a){
        if (root[a] == a){
            return root[a];
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

// 프림 - O(E log E)
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        Map<Integer, List<int []>> g = new HashMap<>();
        PriorityQueue<int []> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));

        for(int i = 1; i <= v; i++){
            g.put(i, new ArrayList<>());
        }

        for(int i = 0; i < e; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            g.get(a).add(new int [] {b, c});
            g.get(b).add(new int [] {a, c});
        }

        int ans = 0;
        int nodeCnt = 0;
        boolean[] visited = new boolean[v + 1];

        pq.offer(new int [] {1, 0});

        while(!pq.isEmpty()){
            int [] curr = pq.poll();

            int cx = curr[0];
            int cw = curr[1];

            if(visited[cx]) continue;
            visited[cx] = true;

            ans += cw;
            nodeCnt++;

            if(nodeCnt == v) break;

            for(int[] nxt : g.get(cx)) {
                int nx = nxt[0];
                int nw = nxt[1];

                if (visited[nx]) continue;

                pq.offer(new int [] {nx, nw});
            }
        }

        System.out.println(ans);
    }
}