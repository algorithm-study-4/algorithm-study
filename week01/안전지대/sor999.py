"""
완전탐색
O(9*n^2) = O(n^2)
"""
def solution(board):

    n = len(board)
    visited = set()
    
    
    def check(cx, cy):
        visited.add((cx, cy))
        
        for dx in (-1, 0, 1):
            for dy in (-1, 0, 1):
                nx, ny = cx + dx, cy + dy
                if 0 <= nx < n and 0 <= ny < n:
                    visited.add((nx, ny))
    
    for i in range(n):
        for j in range(n):
            if board[i][j] == 1:
                check(i, j)
    
    return n * n - len(visited)