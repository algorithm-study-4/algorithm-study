T, n = map(int, input().split()) # 물품의 가치(T), 동전의 수(n)
coins = list(map(int, input().split())) # 동전의 종류 입력받기

dp = [99999] * (T + 1) # 0~T 까지 99999로 채워진 dp 배열 만들기
dp[0] = 0 # 0원 -> 0 으로 채우기

for i in range(1, T+1): # 0~T원까지 반복하고
    for coin in coins: # 동전의 종류마다 순회하면서
        if i - coin >= 0: # 만약 만들어야 하는 금액 - coin 이 0보다 크거나 같을 경우
            dp[i] = min(dp[i], dp[i-coin] + 1) # dp[i] 자리를 dp[i]와 dp[i-coin]+1 중 작은 수로 채운다

if dp[T] == 99999:
  print("impossible")
else:
  print(dp[T])