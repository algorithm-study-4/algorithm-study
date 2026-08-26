class Solution {
    public int solution(int n) {
        final int MOD = 1_000_000_007;
        
        // n이 홀수면 절대 채울 수 없음 (넓이가 홀수라 타일로 안 나눠짐)
        if (n % 2 != 0) return 0;
        
        // dp[i] = 가로 길이가 i인 3×i 바닥을 채우는 방법의 수
        long[] dp = new long[n + 1];
        
        dp[0] = 1; // 빈 바닥 (채울 게 없으니 1가지 - 아무것도 안 함)
        if (n >= 2) dp[2] = 3; // 3×2 바닥을 채우는 방법은 3가지 (직접 세어서 확인된 값)
        
        // 짝수 칸만 채워나감 (홀수 칸은 애초에 답이 0이라 건너뜀)
        for (int i = 4; i <= n; i += 2) {
            // 4를 곱하고 이전 이전 값을 빼는 형태의 점화식
            // 나머지 연산 중 음수가 나올 수 있으므로 +MOD 해준 뒤 다시 나머지 처리 (음수 방지)
            dp[i] = ((4 * dp[i - 2] - dp[i - 4]) % MOD + MOD) % MOD;
        }
        
        return (int) dp[n];
    }
}
