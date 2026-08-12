"""
터트려져 사라진 인형의 개수
1. 담기: 인형 선택 -> stk push
2. 터트리기: stk 같은 것 연속 2개 -> 터짐

"""

"""
O(len(board) * len(moves))
"""
"""
def solution(board, moves):
    n = len(board)
    stk = []
    cnt = 0
    
    for j in moves:
        j -= 1
        for i in range(n):
            curr = board[i][j]
            
            if curr == 0:
                continue
                
            if stk and stk[-1] == curr:
                
                stk.pop()
                cnt += 2
            else:           
                stk.append(curr)
                
            board[i][j] = 0
            break
            
    return cnt
"""
"""
각 뽑기 버킷도 스택으로 쓰기
O(n^2 + m)
"""
def solution(board, moves):
    n = len(board)
    stk = []
    cnt = 0
    
    grid = [[board[j][i] for j in reversed(range(n)) if board[j][i] != 0] for i in range(n)]
    
    for j in moves:
        j -= 1
        if grid[j]:
            curr = grid[j].pop()

            if stk and stk[-1] == curr:
                stk.pop()
                cnt += 2
            else:           
                stk.append(curr)
                
    return cnt