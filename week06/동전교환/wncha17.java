import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 물품의 가치 T, 동전의 수 n
        StringTokenizer st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        int[] coins = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            coins[i] = Integer.parseInt(st.nextToken());
        }

        // dp[i] = 금액 i를 만들기 위한 최소 동전 개수
        int[] dp = new int[t + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0; // 0원을 만드는 데 필요한 동전은 0개

        for (int i = 1; i <= t; i++) {
            for (int coin : coins) {
                // coin으로 i원을 만들 수 있고 (coin <= i),
                // 그 이전 금액(i - coin)이 이미 계산되어 있다면(도달 가능하다면)
                if (coin <= i && dp[i - coin] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        if (dp[t] == Integer.MAX_VALUE) {
            System.out.println("impossible");
        } else {
            System.out.println(dp[t]);
        }
    }
}
