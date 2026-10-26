# 시간복잡도: O(N)
# 문자열을 한 번 순회하기 때문에 O(N), 딕셔너리 조회·갱신은 평균적으로 O(1)

import sys

input = sys.stdin.readline

n = int(input())
arr = [int(i) for i in input().rstrip()]    # 숫자 문자열을 한 자리씩 정수로 변환
answer = 0          # 구간 합 = 구간 길이를 만족하는 구간 개수
cur_sum = 0         # 현재까지 (각 숫자 - 1)의 누적합
sum_cnts = {0: 1}   # 누적합이 key, 해당 누적합이 나온 횟수가 value (아무것도 안 더한 상태 0을 1회로 시작)

for num in arr:
    # 구간 합 = 구간 길이 -> 구간 내 (각 숫자 - 1)의 합 = 0
    # 따라서 각 숫자에서 1을 빼서 누적하면 합이 0인 구간 개수 세기 문제로 바뀜
    cur_sum += num - 1

    # 이전에 같은 누적합이 나온 횟수만큼 조건을 만족하는 구간이 생기므로 answer에 더해줌
    answer += sum_cnts.get(cur_sum, 0)

    sum_cnts[cur_sum] = sum_cnts.get(cur_sum, 0) + 1    # 누적합이 나온 횟수 갱신

print(answer)