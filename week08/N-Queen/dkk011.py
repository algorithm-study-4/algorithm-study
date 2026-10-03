# O(N × N!)
# DFS 탐색: O(N!)
# is_safe 검사: O(N)
def solution(n):
    # N x N 체스판 -> 0: 빈 칸, 1: 퀸
    board = [[0] * n for _ in range(n)]

    # 현재 (r, c) 위치에 퀸을 놓아도 되는지 검사
    # 위에서부터 한 행씩 퀸을 놓기 때문에 현재 행보다 위쪽만 검사하면 됨
    def is_safe(r, c):
        # 같은 열에 퀸이 있는지
        for i in range(r):
            if board[i][c] == 1:
                return False

        # 왼쪽 위 대각선에 퀸이 있는지
        i, j = r - 1, c - 1
        while i >= 0 and j >= 0:
            if board[i][j] == 1:
                return False
            i -= 1
            j -= 1

        # 오른쪽 위 대각선에 퀸이 있는지
        i, j = r - 1, c + 1
        while i >= 0 and j < n:
            if board[i][j] == 1:
                return False
            i -= 1
            j += 1

        # 퀸 놓기 가능
        return True

    def dfs(row):
        # 모든 행에 퀸 놓기 성공 -> 경우의 수 1 증가
        if row == n:
            return 1

        # 현재 상태에서 만들 수 있는 전체 경우의 수
        count = 0

        # 현재 행의 각 열에 퀸 놓아보면서 모든 경우의 수 탐색
        for col in range(n):
            # 현재 위치에 퀸을 놓을 수 있는지 검사
            if not is_safe(row, col):
                continue

            board[row][col] = 1     # 퀸 놓기
            count += dfs(row + 1)   # 다음 행 탐색
            board[row][col] = 0     # 다음 열 시도하기 전에 퀸 제거

        return count

    # 0번째 행부터 시작
    return dfs(0)