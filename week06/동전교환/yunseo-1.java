import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken()); // 목표 금액
        int n = Integer.parseInt(st.nextToken()); // 동전 종류 개수
        
        int[] coins = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            coins[i] = Integer.parseInt(st.nextToken());
        }
        
        // dp[i] = i원을 만드는 데 필요한 최소 동전 개수
        int[] dp = new int[t + 1];
        
        // 아직 계산 안 된 상태를 표현하기 위해 "무한대"에 가까운 큰 값으로 초기화
        // (나중에 이 값이 그대로 남아있으면 그 금액은 만들 수 없다는 뜻)
        Arrays.fill(dp, Integer.MAX_VALUE);
        
        // 0원을 만드는 데는 동전 0개가 필요함 (아무것도 안 씀)
        dp[0] = 0;
        
        // 1원부터 T원까지, 작은 금액부터 순서대로 답을 채워나감 
        for (int i = 1; i <= t; i++) {
            // 각 동전 종류를 하나씩 다 시도해봄
            for (int coin : coins) {
                // i원에서 이 동전 하나를 뺀 금액(i-coin)을 이미 만든 적 있는지 확인
                if (i - coin >= 0 && dp[i - coin] != Integer.MAX_VALUE) {
                    // dp[i-coin]원을 만드는 방법에 동전 1개(coin)만 추가하면 i원이 됨
                    // 여러 동전 중 "가장 적은 개수가 되는 경우"를 선택 (min 비교)
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        
        // dp[t]가 여전히 무한대라면, 어떤 조합으로도 T원을 만들 수 없음
        if (dp[t] == Integer.MAX_VALUE) {
            System.out.println("impossible");
        } else {
            System.out.println(dp[t]);
        }
    }
}