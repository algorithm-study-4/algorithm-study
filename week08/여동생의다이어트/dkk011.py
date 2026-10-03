# O(2 ^ B)
# 각 음식마다 먹을지 / 안 먹을지 두 가지 경우 탐색하므로
import sys

input = sys.stdin.readline

C, B = map(int, input().rstrip().split())
calories = list(map(int, input().rstrip().split()))

def dfs(i, total):
    # 현재까지 먹은 칼로리가 C를 넘으면 더 탐색할 필요 없음
    if total > C:
        return 0

    # 모든 음식 확인했으면 현재까지 칼로리 합 반환
    if i == B:
        return total

    # i번째 음식을 먹지 않는 경우와 먹는 경우 중 칼로리 합이 더 큰 경우 선택
    return max(dfs(i + 1, total), dfs(i + 1, total + calories[i]))
    
print(dfs(0,0))