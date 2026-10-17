import java.io.*;
import java.util.*;

public class Main {
    static private int [] root;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        int n = Integer.parseInt(br.readLine());

        root = new int [n];

        for(int i = 0; i < n; i++){
            root[i] = i;
        }

        boolean isCycle = false;

        for(int i = 0; i < n && !isCycle; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int j = 0; j < n; j++){
                int v = Integer.parseInt(st.nextToken());

                if(i >= j) continue;

                if(v == 1){
                    if(!union(i, j)){
                        isCycle = true;
                    }
                }
            }

        }

        System.out.println(isCycle ? "WARNING" : "STABLE");
    }

    static private int find(int a){
        if (root[a] == a){
            return root[a];
        }

        root[a] = find(root[a]);

        return root[a];
    }

    static private boolean union(int a, int b){
        a = find(a);
        b = find(b);

        if (a == b) return false;

        root[a] = b;

        return true;
    }
}