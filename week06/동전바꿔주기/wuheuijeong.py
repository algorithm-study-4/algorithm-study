# 입력 받기

T = int(input()) # 지폐의 금액
k = int(input()) # 동전의 가짓수

# 동전의 금액과 개수 입력받기
coins = []
for _ in range(k):
  coins.append(list(map(int, input().split())))

# 출력해야 하는 것 : 동전 교환 방법의 가짓수
# 방법이 없을 때 : 0 출력

# 2차원 배열 만들기 -> 가로: 만들어야 하는 금액
dp = list([0] * (T+1) for _ in range(k+1))

# 기본적으로 어떤 동전을 사용하더라도 0원을 만드는 방법은 1가지 -> 0개 쓰기
for d in range(len(dp)):
  dp[d][0] = 1

for i in range(1, k+1): # 동전의 개수 추가되는 행
  for j in range(1, T+1): # 금액 부분
    
    # 동전의 개수만큼 반복하기 (0개 사용, 1개 사용, c개 사용 ...)
    for c in range(coins[i-1][1] + 1):
      if j >= c * coins[i-1][0]: # 음수 되는 것 방지

        # dp[i-1][j-(c * coins[i-1][0])] 을 더해줘야 함
        # c * coins[i-1][0] : 동전 개수 * i번째 동전의 금액
        # j - (c * coins[i-1][0]) : 아직 안 채워진 부분의 금액
        # dp[i-1][j-(c * coins[i-1][0])] : 남은 금액을 이전 동전들만으로 만드는 방법이 몇 개인지
        dp[i][j] += dp[i-1][j-(c * coins[i-1][0])]

print(dp[k][T])