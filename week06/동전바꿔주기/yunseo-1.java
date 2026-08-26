import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int t = Integer.parseInt(br.readLine().trim()); // 목표 금액
        int k = Integer.parseInt(br.readLine().trim()); // 동전 종류 수
        
        int[] price = new int[k]; // 각 동전의 금액
        int[] count = new int[k]; // 각 동전의 개수
        
        for (int i = 0; i < k; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            price[i] = Integer.parseInt(st.nextToken());
            count[i] = Integer.parseInt(st.nextToken());
        }
        
        // dp[j] = 금액 j를 만드는 방법의 수
        long[] dp = new long[t + 1]; // 2^31 미만이지만 중간 계산 안전하게 long 사용
        dp[0] = 1; // 0원을 만드는 방법 = "아무 동전도 안 쓴다" 1가지
        
        for (int i = 0; i < k; i++) {
            // 이번 동전 종류를 고려하기 전 상태를 고정해둬야,
            // "이 동전을 몇 개 쓸지"의 각 경우가 서로 겹쳐 세지 않음
            long[] dpPrev = dp.clone();
            
            for (int cnt = 1; cnt <= count[i]; cnt++) {
                int usedAmount = price[i] * cnt; // 이 동전을 cnt개 썼을 때의 금액
                if (usedAmount > t) break; // 목표 금액을 넘으면 더 볼 필요 없음
                
                // dpPrev[j - usedAmount] (이 동전 쓰기 전 상태)를 더해서 누적
                for (int j = usedAmount; j <= t; j++) {
                    if (dpPrev[j - usedAmount] > 0 || j - usedAmount == 0) {
                        dp[j] += dpPrev[j - usedAmount];
                    }
                }
            }
        }
        
        System.out.println(dp[t]);
    }
}