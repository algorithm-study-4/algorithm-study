"""
방법1. bottom up
O(n)
"""
n = int(input())
MOD = 10**9 + 7

dp = [0] * (n + 2)

dp[1], dp[2] = 1, 1
for i in range(3, n + 1):
    dp[i] = (dp[i - 1] + dp[i - 2]) % MOD

print(dp[n])

"""
방법2. top down - 메모이제이션
O(n)
"""

import sys
sys.setrecursionlimit(10**6)
n = int(input())
MOD = 10**9 + 7

dp = [-1] * (n + 1)

def fibo(i):
    if i <= 2:
        dp[i] = 1
        return 1
    
    if dp[i] != -1:
        return dp[i]

    dp[i] = (fibo(i - 1) + fibo(i - 2)) % MOD
    return dp[i]
print(fibo(n))