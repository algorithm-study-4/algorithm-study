def solution(board):
    rows, cols = len(board), len(board[0])
    bombs = [(i, j) for i in range(rows) for j in range(cols) if board[i][j]]

    dx = [-1, -1, -1, 0, 0, 1, 1, 1]
    dy = [-1, 0, 1, -1, 1, -1, 0, 1]

    for i, j in bombs:
        for k in range(8):
            nx = i + dx[k]
            ny = j + dy[k]

            if 0 <= nx < rows and 0 <= ny < cols:
                board[nx][ny] = 1

    return sum(j == 0 for i in board for j in i)