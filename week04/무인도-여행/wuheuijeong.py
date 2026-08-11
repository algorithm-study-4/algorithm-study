from collections import deque

def solution(maps):
    n, m = len(maps), len(maps[0])
    visited = [[False] * m for _ in range(n)]
    answer = []
    
    
    dr = [-1, 1, 0, 0]
    dc = [0, 0, -1, 1]
    
    def bfs(r, c):
        sum = 0
        queue = deque()
        queue.append((r, c))
        
        sum += int(maps[r][c])
        visited[r][c] = True
        
        while queue:
            cr, cc = queue.popleft()
            
            for i in range(4):
                nr = cr + dr[i]
                nc = cc + dc[i]
                
                if nr <= -1 or nr >= n or nc <= -1 or nc >= m:
                    continue
                
                if maps[nr][nc] != 'X' and not visited[nr][nc]:
                    sum += int(maps[nr][nc])
                    visited[nr][nc] = True
                    queue.append((nr, nc))
        return sum
    
    
    for r in range(n):
        for c in range(m):
            if maps[r][c] != 'X' and not visited[r][c]:
                answer.append(bfs(r, c))
    
    if not answer:
        answer = [-1]
        
        
    return sorted(answer)
