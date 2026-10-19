import java.io.*;
import java.util.*;

public class Main {
    static private int [] root;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        root = new int [n + 1];

        for(int i = 1; i <= n; i++){
            root[i] = i;
        }

        for(int i = 0; i < q; i++){
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b =Integer.parseInt(st.nextToken());

            if (k == 0){
                sb.append(find(a) == find(b) ? "YES" : "NO").append("\n");
            }else {
                union(a, b);
            }
        }

        System.out.print(sb);
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