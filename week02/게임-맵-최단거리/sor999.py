"""
bfs
한점 -> 다른점, 최단 경로
O(nm)
"""
from collections import deque
def solution(maps):
    answer = 0
    
    n = len(maps)
    m = len(maps[0])
    
    dx = [-1,1,0,0]
    dy = [0,0,1,-1]
    
    q = deque()
    q.append((0, 0, 1))
    maps[0][0] = 0
    
    while q:
        cx, cy, cnt = q.popleft()
        
        if cx == n - 1 and cy == m - 1:
            return cnt
        
        for i in range(4):
            nx, ny = cx + dx[i], cy + dy[i]
            
            if 0 <= nx < n and 0 <= ny < m and maps[nx][ny] == 1:
                maps[nx][ny] = 0
                q.append((nx, ny, cnt + 1))
    
    return -1