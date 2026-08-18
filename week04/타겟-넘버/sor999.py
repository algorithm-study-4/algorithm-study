"""
방법1. dfs
O(2^(n+1)) = O(2^n)
=> 2^21 = 2 * 2^10 * 2^10 = 2*10^3 * 10^3 = 2 * 10^6
"""
def solution(numbers, target):
    answer = 0
    n = len(numbers)
    
    def dfs(curr, depth):
        nonlocal answer
        
        if depth == n:
            if curr == target:
                answer += 1
            return

        dfs(curr + numbers[depth], depth + 1)
        dfs(curr - numbers[depth], depth + 1)
   
    dfs(0, 0)
    
    return answer
"""
방법2. bfs
O(2^n)
"""
from collections import deque
def solution(numbers, target):
    answer = 0
    n = len(numbers)
    
    q = deque()
    q.append((0, 0))
    
    while q:
        curr, depth = q.popleft()
        
        if depth == n:
            if curr == target:
                answer += 1
            continue
            
        q.append((curr + numbers[depth], depth + 1))
        q.append((curr - numbers[depth], depth + 1))
        
    return answer