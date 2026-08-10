# O(N^2)
# for문에서 O(N) + while문에서 O(N) => O(N^2)
from collections import deque

def solution(n, computers):
    answer = 0
    visited = [False] * n
    
    for i in range(n):
        # 아직 방문하지 않은 컴퓨터라면 새로운 네트워크를 발견한 것
        if not visited[i]:
            queue = deque()     # 큐 생성
            queue.append(i)     # 현재 컴퓨터를 큐에 추가
            visited[i] = True   # 방문 처리
            
            while queue:
                current = queue.popleft()   # 현재 컴퓨터를 큐에서 꺼냄
                for j in range(n):          # 현재 컴퓨터와 연결된 모든 컴퓨터 확인

                    # 아직 방문하지 않았고 연결되어 있다면
                    if not visited[j] and computers[current][j] == 1:
                        visited[j] = True   # 방문 처리
                        queue.append(j)     # 큐에 추가

            answer += 1     # 새로운 네트워크를 발견했으므로 증가
    
    return answer