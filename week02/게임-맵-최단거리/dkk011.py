# N = len(maps), M = len(maps[0])
# O(N * M)

from collections import deque

def solution(maps):
    n = len(maps)
    m = len(maps[0])
    
    # 방문 여부 저장, 값이 1이면 이미 방문
    visited = [[0] * m for _ in range(n)]
    
    # 상, 하, 좌, 우 이동
    dx = [-1, 1, 0, 0]
    dy = [0, 0, -1, 1]
    
    def bfs(x, y):
        queue = deque([(x, y)])     # 시작 위치를 큐에 추가
        visited[x][y] = 1           # 방문 처리
        
        while queue:
            x, y = queue.popleft()  # 현재 위치 꺼냄
            
            # 현재 위치에서 4방향 탐색
            for i in range(4):
                nx = x + dx[i]
                ny = y + dy[i]
                
                # maps 범위 확인
                if 0 <= nx < n and 0 <= ny < m:
                    
                    # 이동 가능한 위치이고, 아직 방문하지 않은 경우
                    if maps[nx][ny] == 1 and visited[nx][ny] == 0:
                        visited[nx][ny] = 1             # 방문 처리
                        maps[nx][ny] = maps[x][y] + 1   # 이전 칸 거리 + 1 저장
                        queue.append((nx, ny))          # 다음 탐색 위치로 추가
        
        # 상대 팀 진영 방문 시 최단 거리 반환, 아니라면 -1
        return maps[n - 1][m - 1] if visited[n - 1][m - 1] else -1

    return bfs(0, 0)