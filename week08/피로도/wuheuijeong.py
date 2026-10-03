def solution(k, dungeons):
    n = len(dungeons) # 던전의 개수
    visited = [False] * n # 방문 체크
    answer = 0 # 최대 던전 수 answer
    
    def dfs(piro, count):
        
        nonlocal answer # nonlocal: 지역변수 X 체크
        answer = max(answer, count) # answer에 우선 현재까지의 answer과 현재 count 중 큰 거 입력하기
        
        for i in range(n): # 던전 개수만큼 순회
            
            if not visited[i]: # i번 던전 방문 안 했다면
                need, cost = dungeons[i] # 필요 피로도, 소모 피로도 변수에 담기
                
                if piro >= need: # 현재 피로도가 필요 피로도보다 클 경우
                    visited[i] = True # 방문 가능 -> 방문 체크
                    dfs(piro - cost, count + 1) # 그 상태에서 현재 피로도 빼고, 방문 던전 개수 + 1 처리해서 재귀함수 돌리기
                    visited[i] = False # 다시 방문여부 되돌리기

    dfs(k, 0)
    return answer