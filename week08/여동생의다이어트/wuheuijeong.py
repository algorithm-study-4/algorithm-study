C, B = map(int, input().split())
foods = list(map(int, input().split()))

def solution(C, foods):

    best = 0

    def dfs(idx, total):
        nonlocal best

        if total > C: # 칼로리가 이미 C가 넘은 경우 -> 가지치기
            return best # 끝

        # best 갱신 - 현재 best 와 total 비교해서 큰 값을 best로
        best = max(best, total)

        if idx == B: # B번째 음식까지 다 먹는 경우 (더이상 음식 없음)
            return best

        if total + foods[idx] <= C: # (조건)
            dfs(idx + 1, total + foods[idx]) # 이번 idx 음식 먹는 경우 -> 조건 필요 (먹었을 때 칼로리 C보다 작/같아야 함)

        dfs(idx + 1, total) # 이번 거 안 먹는 건 조건 없이 진행 가능
        
        return best

    dfs(0,0) 
    return best

print(solution(C, foods))