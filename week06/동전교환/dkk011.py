# O(T * n)
import sys

input = sys.stdin.readline

T, n = map(int, input().rstrip().split())
coins = list(map(int, input().rstrip().split()))

# dp[i] = 가치 i를 교환하는데 필요한 최소 동전 개수
# 동전의 최소 개수가 1이므로 충분히 큰 값인 T + 1로 초기화
dp = [T + 1] * (T + 1)
dp[0] = 0

for i in range(1, T + 1):
    for c in coins:
        
        # 물품 가치 i보다 동전 가치 c가 작은 경우
        if c <= i:
            
            # c원 동전 하나 사용하면 나머지 i - c원을 만들어야 한다
            # min으로 더 적은 개수 선택
            dp[i] = min(dp[i], dp[i - c] + 1)

print("impossible" if dp[T] > T else dp[T])