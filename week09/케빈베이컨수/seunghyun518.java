import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] answer = new int[n];
        int[][] edge = new int[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(i != j){
                    edge[i][j] = 999999;
                }
            }
        }
        int min = 0;

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;
            edge[a][b] = 1;
            edge[b][a] = 1;
        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                for(int k = 0; k < n; k++){
                    edge[k][j] = Math.min(edge[k][j], edge[k][i] + edge[i][j]);
                }
            }
        }

        for(int i =0; i<n; i++){
            for(int j = 0; j<n; j++){
                answer[i] += edge[i][j];
            }
        }

        for(int i = 0; i < n; i++){
            if(answer[i] < answer[min]){
                min = i;
            }
        }

        System.out.println(min + 1);

    }
}