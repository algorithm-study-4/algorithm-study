"""
?: 지폐 -> 동전 교환 시 방법의 수
dp[i][j] = i번쨰 동전까지만 사용해서 j원을 만드는 방법의 수

O(k * t * cnt)
"""

import sys
input = sys.stdin.readline

t = int(input()) # 금액
k = int(input()) # 동전 개수
coins = list(tuple(map(int, input().split())) for _ in range(k)) # 금액, 개수

dp = [[0] * (t + 1) for _ in range(k + 1)]
dp[0][0] = 1

for i in range(1, k + 1): # p, n
    money, cnt = coins[i - 1]

    for j in range(t + 1):
        for c in range(cnt + 1): # 동전 0 ~ cnt개 사용
            if j < c * money:
                break
            dp[i][j] += dp[i - 1][j - c * money] # 사용 가능하면 갱신

print(dp[k][t])  

"""
슬라이딩 윈도우
dp[i] = j원을 만드는 방법의 수
O(kt)
"""

import sys
input = sys.stdin.readline

t = int(input()) # 금액
k = int(input()) # 동전 개수
coins = list(tuple(map(int, input().split())) for _ in range(k)) # 금액, 개수


dp = [0] * (t + 1)
dp[0] = 1

for money, cnt in coins:
    temp = [0] * (t + 1)

    for j in range(t + 1):
        if j >= money:
            temp[j] += temp[j - money]

        temp[j] += dp[j]
 
        if j >= (cnt + 1) * money:
            temp[j] -= dp[j - (cnt + 1) * money]

    dp = temp

print(dp[t])


 