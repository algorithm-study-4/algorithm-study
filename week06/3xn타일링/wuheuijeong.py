# 완전탐색 코드 - 예시 확인용

def find_empty(board, rows, cols):
  for r in range(rows):
    for c in range(cols):
      if board[r][c] == 0:
        return (r, c)
  return None

def count_ways(board, rows, cols):
  # 빈 칸(0) 찾기
  pos = find_empty(board, rows, cols)
  if pos is None:
    return 1

  r, c = pos
  total = 0

  if c+1 < cols and board[r][c+1] == 0:
    board[r][c] = 1
    board[r][c+1] = 1
    total += count_ways(board, rows, cols)
    board[r][c] = 0
    board[r][c+1] = 0
  
  if r+1 < rows and board[r+1][c] == 0:
    board[r][c] = 1
    board[r+1][c] = 1
    total += count_ways(board, rows, cols)
    board[r][c] = 0
    board[r+1][c] = 0

  return total


board = [[0]*6 for _ in range(3)]  # 3행 4열, 다 빈칸
print(count_ways(board, 3, 6))




# -----------------------------------------------------------------

# 정답 코드 (점화식 포함)

def solution(n):
    
    answer = 0
    
    dp = [0] * (n+1)
    
    dp[0] = 0
    dp[1] = 0
    dp[2] = 3
    dp[3] = 0
    dp[4] = 11

    for i in range(5, n+1):
        if i % 2 == 1:
            dp[i] = 0
        else:
            dp[i] = 4 * dp[i-2] - dp[i-4]
            
    answer = dp[n] % 1000000007
    
    return answer