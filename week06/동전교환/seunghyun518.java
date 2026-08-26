import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 거슬러줘야하는 돈
        int t = Integer.parseInt(st.nextToken());

        // 동전의 종류 수
        int n = Integer.parseInt(st.nextToken());

        // 채울 dp리스트
        int[] dp = new int[t + 1];
        Arrays.fill(dp, 301);
        dp[0] = 0;

        // 동전들 입력받기
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // dp 리스트 채우기
        for(int i = 1; i <= t; i++){
            // 모든 동전의 종류에 대해 검사할거다
            for(int j : arr){
                // 만약 동전이 들어갈 수 있으면
                if(i >= j){
                    // 원래 있던 최솟값과 비교해서 더 작은 값을 넣을거다.
                    dp[i] = Math.min(dp[i], dp[i-j] + 1);
                }
            }
        }
        if(dp[t] == 301){
            System.out.println("impossible");
        }
        else{
            System.out.println(dp[t]);
        }
    }
}