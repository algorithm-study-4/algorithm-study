"""
연결 요소의 개수

방법1. dfs
O(n^2)
"""
def solution(n, computers):
    answer = 0
    
    visited = [False] * n
    
    def dfs(curr):
        visited[curr] = True
        for nxt in range(n):
            if computers[curr][nxt] == 0 or visited[nxt]:
                continue
            dfs(nxt)
            
    for curr in range(n):
        if visited[curr]:
            continue
        dfs(curr)
        answer += 1
               
    return answer
"""
방법2. bfs
O(n^2)
"""
from collections import deque
def solution(n, computers):
    answer = 0
    
    visited = [False] * n
    
    def bfs(start):
        q = deque()
        visited[start] = True
        q.append(start)
        
        while q:
            curr = q.popleft()
            
            for nxt in range(n):
                if computers[curr][nxt] == 0 or visited[nxt]:
                    continue
                visited[nxt] = True
                q.append(nxt)

    for curr in range(n):
        if visited[curr]:
            continue
        bfs(curr)
        answer += 1
               
    return answer

"""
방법3. 유니온 파인드
O(n^2logn)
"""

def solution(n, computers):
    answer = 0
    
    root = [i for i in range(n)]
    def union(a, b):
        a = find(a)
        b = find(b)
        
        if a != b:
            root[a] = b
            
    def find(a):
        if root[a] == a:
            return a
        
        root[a] = find(root[a])
        
        return root[a]
    
    for curr in range(n):
        for nxt in range(curr + 1, n):
            if computers[curr][nxt] == 1:
                union(curr, nxt)

    s = set()
    
    for curr in range(n):
        s.add(find(curr))

    answer = len(s)
               
    return answer