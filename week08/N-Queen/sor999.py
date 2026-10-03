def solution(n):
    board = [0] * n

    def is_valid(i):
        for j in range(i):
            if board[i] == board[j] or abs(board[i] - board[j]) == abs(i - j):
                return False
        return True

    def dfs(i):
        if i == n:
            return 1

        cnt = 0

        for c in range(n):
            board[i] = c
            if is_valid(i):
                cnt += dfs(i+1)

        return cnt

    return dfs(0)