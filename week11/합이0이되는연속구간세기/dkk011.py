# 시간복잡도: O(N)
# 배열을 한 번 순회하기 때문에 O(N), 딕셔너리 조회·갱신은 평균적으로 O(1)

import sys

input = sys.stdin.readline

n = int(input())
arr = list(map(int, input().split()))
answer = 0          # 합이 0인 연속 구간 개수
cur_sum = 0         # 현재까지 누적합
sum_cnts = {0: 1}   # 누적합이 key, 해당 누적합이 나온 횟수가 value (아무것도 안 더한 상태 0을 1회로 시작)

for num in arr:
    cur_sum += num  # 누적합 갱신

    # 이전에 같은 누적합이 나온 횟수만큼 합이 0인 구간이 생기므로 answer에 더해줌
    answer += sum_cnts.get(cur_sum, 0)

    sum_cnts[cur_sum] = sum_cnts.get(cur_sum, 0) + 1 # 누적합이 나온 횟수 갱신

print(answer)