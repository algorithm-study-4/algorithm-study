"""
교환할 수 있는 최소 동전 개수
- dp[i] = dp[i - coin] + 1 을 모든 코인에 적용해봤을 때 최솟값 : i 가치를 교환하기 위한 최소 동전 개수

dp
O(t)
"""
t, n = map(int, input().split())
coins = list(map(int, input().split()))
INF = float('inf')
dp = [INF] * (t + 1)
dp[0] = 0

for i in range(1, t + 1):
    for coin in coins:
        dp[i] = min(dp[i], dp[i - coin] + 1)

print("impossible" if dp[t] == INF else dp[t])
