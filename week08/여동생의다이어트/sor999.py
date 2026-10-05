
"""
섭취량 c를 넘지 않는 조건에서, 섭취할 수 있는 최대 칼로리 출력

문제 유형: 조합 활용
시간복잡도: O(2^b)
"""
# c: 하루 칼로리 한도, b: 음식 수
c, b = map(int, input().split())

foods = list(map(int, input().split()))

# c를 넘지 않는 최대 칼로리 합
ans = 0

def dfs(total, at): # total: 지금까지 고른 음식들의 칼로리 합 / at: 이번에 고를 수 있는 음식의 시작 인덱스
    global ans

    # 가지치기 - c를 넘으면 이 경로는 더이상 볼 필요가 없으므로 종료
    if total > c:
        return

    # 유효한 값들 중 최댓값 갱신
    ans = max(ans, total)

    # at ~ b-1 사이에서 다음으로 먹을 음식 선택 
    for i in range(at, b):
        # total + foods[i]: i번 음식을 먹어서 total에 더함
        # i + 1: 이전 음식은 선택하지 않음
        dfs(total + foods[i], i + 1)

dfs(0, 0)

print(ans)