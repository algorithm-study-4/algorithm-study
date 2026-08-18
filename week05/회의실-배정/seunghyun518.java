import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        int n = Integer.parseInt(br.readLine());
        int answer = 0;
        int[][] arr = new int[n][2];
        int reserved = 0;

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr, (a, b) -> a[1] - b[1]);

        for(int i = 0; i < n; i++){
            if(reserved <= arr[i][0]){
                reserved = arr[i][1];
                answer += 1;
            }
        }
        System.out.println(answer);
    }
}

// 시간이 짧은 회의 vs 빨리 끝나는 회의(시간이 짧은 회의(정답 안나옴)), 빨리 끝나는 회의로 해야함
