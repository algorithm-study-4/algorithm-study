def solution(board):
    answer = 0
    
    n = len(board)
    bomb = []
    
    # 지뢰 위치 미리 확인하기
    for i in range (n):
        for j in range (n):
            if board[i][j] == 1:
                bomb.append((i,j))
                
    # 지뢰 위치 순회하며 위험 지역 표시하기
    dx = [-1, -1, -1, 0, 0, 0, 1, 1, 1]
    dy = [-1, 0, 1, -1, 0, 1, -1, 0, 1]
    
    danger = [[0] * n for _ in range(n)] # 빈 보드판
    
    for r, c in bomb:
        for k in range(9):
            nr, nc = r + dx[k], c + dy[k]
            # 판 밖에 나가지 않는지 검증
            if 0 <= nr < n and 0 <= nc < n:
                danger[nr][nc] = 1
    
    # 안전한 지역 칸의 수 세기
    
    answer = 0
    
    for i in range(n):
        for j in range(n):
            if danger[i][j] == 0:
                answer += 1
    
                
    return answer