import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        int t = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());
        int[][] coins = new int[k][2];

        int[] dp = new int[t + 1];
        dp[0] = 1;

        for(int i = 0; i < k; i++){
            st = new StringTokenizer(br.readLine());
            coins[i][0] = Integer.parseInt(st.nextToken());
            coins[i][1] = Integer.parseInt(st.nextToken());
        }
        // 각 동전에 대해 DP 테이블 갱신
        for (int i = 0; i < k; i++) {
            int p = coins[i][0];
            int n = coins[i][1];

            // 역순으로 탐색 (중복 사용 방지)
            for (int j = t; j >= 0; j--) {
                // 현재 동전을 1개부터 n개까지 사용하는 경우를 모두 더함
                for (int l = 1; l <= n; l++) {
                    if (j - p * l < 0) break; // 금액을 초과하면 더 이상 볼 필요 없음
                    dp[j] += dp[j - p * l];
                }
            }
        }

        System.out.println(dp[t]);

    }
}

/*
    t원을 동전으로 교환해줄 건데
    k개의 동전 종류가 정해진 개수만큼 있다(10원 2개, 5원 2개, 3원 3개)
    그 때, t원을 동전으로 바꿔줄 수 있는 경우의 수를 구하여라
*/