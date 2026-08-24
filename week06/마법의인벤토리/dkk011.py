# O(N * W)
import sys

input = sys.stdin.readline
N, W = map(int, input().rstrip().split())

# minerals = [(10, 20), (30, 10), (40, 50), (20, 30), (50, 60)]
minerals = [tuple(map(int, input().rstrip().split())) for _ in range(N)]

# dp[i] = 광물을 최대 한 번만 사용해서 정확히 길이 i를 만들었을 때 최대 무게
dp = [-1] * (W + 1)
dp[0] = 0

for l, h in minerals:
    weight = l * h

    # 이전 상태를 복사해서 한 광물이 연속 두 번 쓰이는 것 방지
    next_dp = dp[:]
    
    for i in range(W + 1):
        
        # 길이 i를 만들 수 있을 때
        if dp[i] != -1:

            # 가로 길이로 놓을 때
            if i + l <= W:
                next_dp[i + l] = max(next_dp[i + l], dp[i] + weight)
            
            # 세로 길이로 놓을 때
            if i + h <= W:
                next_dp[i + h] = max(next_dp[i + h], dp[i] + weight)

    # 계산 결과 반영
    dp = next_dp

print(0 if dp[W] == -1 else dp[W])