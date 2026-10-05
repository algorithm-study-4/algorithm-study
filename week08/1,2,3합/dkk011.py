# O(N)
import sys

input = sys.stdin.readline

N = int(input().rstrip())

# memo[i]는 현재 합이 i일 때 N을 만들 수 있는 경우의 수
# -1은 아직 계산하지 않은 상태
memo = [-1] * (N + 1)

def dfs(cur_sum):
    # N을 만들 수 있으면 1 반환
    if cur_sum == N:
        return 1

    # N이 넘어가서 안 되면 0 반환
    if cur_sum > N:
        return 0

    # 이미 계산했으면 저장된 값 사용
    if memo[cur_sum] != -1:
        return memo[cur_sum]
    
    # 1, 2, 3을 더하는 각각의 경우를 더해서 N을 만드는 전체 경우의 수 저장
    memo[cur_sum] = dfs(cur_sum + 1) + dfs(cur_sum + 2) + dfs(cur_sum + 3)

    return memo[cur_sum]

print(dfs(0))