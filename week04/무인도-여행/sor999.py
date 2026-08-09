"""
- x: 바다
- 1-9: 무인도

각 섬에서 최대 며칠씩 머무를 수 있는지 / -1

방법1. dfs
O(nm + klogk), k <= nm/2
"""
import sys
sys.setrecursionlimit(10**6) # python 기본 재귀 사이즈가 1000이라 필수

def solution(maps):
    answer = []
    
    n = len(maps)
    m = len(maps[0])
    
    maps = [list(r) for r in maps]
    
    dx = [-1, 1, 0, 0]
    dy = [0, 0, -1, 1]
    
    def dfs(cx, cy):
        nonlocal cnt
        cnt += int(maps[cx][cy])
        maps[cx][cy] = "X"
        
        for i in range(4):
            nx, ny = cx + dx[i], cy + dy[i]
            
            if 0 <= nx < n and 0 <= ny < m and maps[nx][ny] != 'X':
                dfs(nx, ny)
    
    for i in range(n):
        for j in range(m):
            if maps[i][j] == 'X':
                continue
            cnt = 0
            dfs(i, j)
            
            answer.append(cnt)
            
    answer.sort()
    
    return answer if answer else [-1]

"""
방법2. bfs
"""
from collections import deque
def solution(maps):
    answer = []
    
    n = len(maps)
    m = len(maps[0])
    
    maps = [list(r) for r in maps]
    
    dx = [-1, 1, 0, 0]
    dy = [0, 0, -1, 1]
    
    q = deque()
    
    def bfs(sx, sy):
        cnt = int(maps[sx][sy])
        q.append((sx, sy))
        maps[sx][sy] = 'X'
        
        while q:
            cx, cy = q.popleft()
            for i in range(4):
                nx, ny = cx + dx[i], cy + dy[i]
                
                if 0 <= nx < n and 0 <= ny < m and maps[nx][ny] != 'X':
                    cnt += int(maps[nx][ny])
                    maps[nx][ny] = 'X'
                    q.append((nx, ny))
        return cnt

    for i in range(n):
        for j in range(m):
            if maps[i][j] == 'X':
                continue
            
            answer.append(bfs(i, j))
            
    answer.sort()
    
    return answer if answer else [-1]
    
    