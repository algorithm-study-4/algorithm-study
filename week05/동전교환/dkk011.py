# O(4) -> O(1)
import sys

input = int(sys.stdin.readline())

count = 0
for i in [500, 100, 50, 10]:    # 큰 단위부터 차례대로
  count += input // i           # 현재 금액에서 동전을 최대한 많이 사용
  input %= i                    # 동전을 사용하고 남은 금액으로 업데이트

print(count)