# 같은 대각선 위에 있는지 확인할 때
# abs(행1 - 행2) == abs(열1 - 열2) -> 대각선에 있음

# 퀸(Queen)은 가로, 세로, 대각선으로 이동, n <= 12

def solution(n):
    answer = 0
    col_used = [False] * n # 열 체크하기
    cols = [] # cols[row] = 행에 놓인 퀸의 열 번호
    
    # 대각선 확인해서 놓을 수 있는 자리인지 확인
    def is_valid(row, col):
        for prev_row in range(row): # 현재 row보다 이전 row들 순회
            prev_col = cols[prev_row] # prev_col 확인하기
            if abs(prev_row - row) == abs(prev_col - col): # 대각선 확인 (현재 row, col 과 이전 row, col)
                return False # 대각선이면 못 놓음 -> False
        return True # 대각선이 아니면 놓을 수 있음 -> True
    
    # dfs 구현
    def dfs(row): # row 넣기
        nonlocal answer # 지역변수 X 알리기 (python)
        if row == n: # row 가 n(마지막 행까지 감)이면
            answer += 1 # 경우의 수 answer += 1 : row == n 까지 와서 유효 배치 1개 성공한 것
            return # 리턴하기
        
        for col in range(n): # 0 ~ n-1 col 모두를 돌아가면서
            if not col_used[col] and is_valid(row, col): # 해당 col이 사용되지 않았고, (row, col)에 놓을 수 있다면?
                col_used[col] = True # col_used[col]을 True로 바꾸고
                cols.append(col) # 놓인 열 번호에 입력하기
                dfs(row + 1) # row 한 칸 더 가서 dfs 재호출하기
                cols.pop() # cols에서 빼기
                col_used[col] = False # false로 바꾸기
    
    dfs(0) # 0번 행(row) 부터 시작하기
    return answer