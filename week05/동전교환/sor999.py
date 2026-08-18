"""
방법 1. 그리디
O(1)
"""
import sys
input = sys.stdin.readline

coins = [500, 100, 50, 10]

money = int(input())

ans = 0

for coin in coins:
    ans += money // coin
    money %= coin

print(ans)


"""
방법2. dp
O(money), 2^31, 통과는 함
"""
import sys
input = sys.stdin.readline

coins = [500, 100, 50, 10]

money = int(input())

INF = float('inf')
dp = [INF] * (money + 1)
dp[0] = 0


for i in range(money + 1):
    for coin in coins:
        if coin <= i:
            dp[i] = min(dp[i], dp[i - coin] + 1)

print(dp[money])