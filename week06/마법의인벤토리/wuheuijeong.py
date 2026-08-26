N, W = map(int, input().split()) # 광물의 개수(N), 인벤토리 길이(W)
stones = [] # 광물의 종류를 입력받을 리스트 만들기

# 모든 종류의 광물(N개)를 입력받기
for _ in range(N):
  stones.append(list(map(int, input().split())))

# 2차원 배열로 값 우선 -1로 채우기

# i : 몇 번째 광물까지 고려했는지
# j : 채우려는 길이

dp = [[-1] * (W + 1) for _ in range(N + 1)]
dp[0][0] = 0 # 0번째 광물만 고려해서 0만큼 채우려는 경우는 0으로 설정해둔다


for i in range(1, N+1): # 1번째 ~ N번째 광물을 고려하는 경우
  l, h = stones[i-1] # 가로, 세로의 길이 = stone 에서 i번째 광물

  for j in range(0, W+1): # 채우려는 길이를 0 ~ W 까지로 설정해두고

    dp[i][j] = dp[i-1][j]
    
    # dp[i-1][j-l] 이 -1 이 아니어야 하는 이유: 
    # 이 광물을 놓고 남은 부분도 빈틈없이 완벽하게 채워질 수 있어야 함
    # j-l 만큼을 이전 광물들로 정확히 채워야 함

    if j - l >= 0 and dp[i-1][j-l] != -1:  # 경우 2: 가로로 쓴다
      dp[i][j] = max(dp[i][j], dp[i-1][j-l] + l*h)

    if j - h >= 0 and dp[i-1][j-h] != -1:  # 경우 3: 세로로 쓴다
      dp[i][j] = max(dp[i][j], dp[i-1][j-h] + l*h)



if dp[N][W] == -1:
  print("0")

else:
  print(dp[N][W])