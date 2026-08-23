# O(k * T * n)
import sys

input = sys.stdin.readline

T = int(input().rstrip())
k = int(input().rstrip())
coins = [tuple(map(int, input().rstrip().split())) for _ in range(k)]

# dp[i] = 정확히 금액 i를 만들 수 있는 경우의 수
dp = [0] * (T + 1)
dp[0] = 1

# p는 동전 금액, n은 동전 개수
for p, n in coins:

    # 현재 동전으로 갱신된 결과가 다시 현재 동전 계산에 사용되지 않도록 역순 탐색
    for i in range(T, p - 1, -1):

        # 현재 동전 p를 1부터 n개까지 사용하는 경우
        for j in range(1, n + 1):

            if i - p * j >= 0:
                # (p원 동전 j개를 사용하고 남은 금액)을 만드는 경우의 수 누적
                dp[i] += dp[i - p * j]
            else:
                # i보다 p * j가 더 커지면 불가능하므로 탈출
                break

print(dp[T])
