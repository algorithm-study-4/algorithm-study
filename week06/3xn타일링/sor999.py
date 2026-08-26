"""
O(n^2)
"""
def solution(n):
    answer = 0
    dp = [0] * (n + 2)
    dp[2] = 3
    dp[0] = 1 # 곱셈
    MOD = 1_000_000_007
    
    for i in range(4, n + 1, 2):
        cnt = dp[i - 2] * 3 # 너비 2로 잘림
        for j in range(0, i - 4 + 1, 2): # 너비 2로 잘리지 않음
            cnt += dp[j] * 2
        dp[i] = cnt % MOD
    
    return dp[n]