
"""
광물의 무게를 최대한 무겁게 하자
dp[n][w] = w길이로 놓았을 때 광물 무게의 최댓값, n개까지 사용했을때
O(nw)
"""
import sys

input = sys.stdin.readline

n, w = map(int, input().split())

size = list(tuple(map(int, input().split())) for _ in range(n))


dp = [[-1] * (w + 1) for _ in range(n + 1)] # 물건 0 - n-1, 가치 w

dp[0][0] = 0

for i in range(1, n + 1):
	l, h = size[i - 1]
	weight = l * h
	for j in range(w + 1):
		dp[i][j] = dp[i - 1][j]

		for k in (l, h):
			if j >= k and dp[i - 1][j - k] != -1:
				dp[i][j] = max(dp[i - 1][j - k] + weight, dp[i][j])

print(dp[n][w] if dp[n][w] != -1 else 0)