class Solution {
    static final long MOD = 1_000_000_007L;
    
    public int solution(int n) {
        // n이 홀수라면 절대 채울 수 없음 (가로 2짜리 타일로 3×홀수를 완전히 채우는 건 불가능)
        if (n % 2 == 1) {
            return 0;
        }
        
        // dp[i] = 가로 길이 i인 3×i 직사각형을 채우는 방법의 수 (i는 짝수만 의미 있음)
        long[] dp = new long[n + 1];
        dp[0] = 1; // 채울 필요가 없는 경우(가로 0) -> 1가지(아무것도 안 함)
        if (n >= 2) {
            dp[2] = 3; // 3×2 직사각형을 채우는 방법은 3가지
        }
        
        for (int i = 4; i <= n; i += 2) {
            dp[i] = (4 * dp[i - 2] % MOD - dp[i - 4] % MOD + MOD) % MOD;
        }
        
        return (int) dp[n];
    }
}
