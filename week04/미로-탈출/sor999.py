"""
1 레버 -> 2 문
= S -> L -> E

미로를 빠져나가는 최단 시간 / 탈출 불가 -1
bfs
- O(nm)
"""
from collections import deque

def solution(maps):
    n = len(maps)
    m = len(maps[0])
    
    dx, dy = [-1, 1, 0, 0], [0, 0, -1, 1] 
    
    sx, sy = -1, -1
    ex, ey = -1, -1
    lx, ly = -1, -1
    
    for i in range(n):
        for j in range(m):
            if maps[i][j] == 'S':
                sx, sy = i, j
            elif maps[i][j] == 'L':
                lx, ly = i, j
            elif maps[i][j] == 'E':
                ex, ey = i, j

    def bfs(cx, cy, tx, ty):
        q = deque()
        visited = [[False] * m for _ in range(n)]
        
        q.append((cx, cy, 0))
        visited[cx][cy] = True
        
        while q:
            cx, cy, cnt = q.popleft()

            if cx == tx and cy == ty:
                return cnt
            
            for i in range(4):
                nx, ny = cx + dx[i], cy + dy[i]

                if 0 <= nx < n and 0 <= ny < m and maps[nx][ny] != 'X' and not visited[nx][ny]:
                    visited[nx][ny] = True
                    q.append((nx, ny, cnt + 1))

        return -1
        
    time_sl = bfs(sx, sy, lx, ly)
    time_le = bfs(lx, ly, ex, ey)

    if time_sl == -1 or time_le == -1:
        return -1
    
    return time_sl + time_le

"""
방법2. dfs -> 시간초과
- O(4^nm) = O(4^10000)
"""
import sys
sys.setrecursionlimit(10**6)
def solution(maps):
    n = len(maps)
    m = len(maps[0])
    
    dx, dy = [-1, 1, 0, 0], [0, 0, -1, 1] 
    
    sx, sy = -1, -1
    ex, ey = -1, -1
    lx, ly = -1, -1
    
    for i in range(n):
        for j in range(m):
            if maps[i][j] == 'S':
                sx, sy = i, j
            elif maps[i][j] == 'L':
                lx, ly = i, j
            elif maps[i][j] == 'E':
                ex, ey = i, j

    def dfs(cx, cy, tx, ty, visited, cnt):
        nonlocal min_v
               
        if cx == tx and cy == ty:
            min_v = min(min_v, cnt)
            return

        for i in range(4):
            nx, ny = cx + dx[i], cy + dy[i]
            
            if 0 <= nx < n and 0 <= ny < m and maps[nx][ny] != 'X' and not visited[nx][ny]:
                visited[nx][ny] = True
                dfs(nx, ny, tx, ty, visited, cnt + 1)
                visited[nx][ny] = False

    INF = float('inf')
    visited = [[False] * m for _ in range(n)]
    visited[sx][sy] = True
    min_v = INF
    dfs(sx, sy, lx, ly, visited, 0)
    
    if min_v == INF:
        return -1
    
    time_sl = min_v
    
    
    min_v = INF
    visited = [[False] * m for _ in range(n)]
    visited[lx][ly] = True
    dfs(lx, ly, ex, ey, visited, 0)
    
    if min_v == INF:
        return -1
    
    time_le = min_v
    
    return time_sl + time_le