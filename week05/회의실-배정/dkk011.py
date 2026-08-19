# O(N log N) + O(N) -> O(N log N)
import sys

input = sys.stdin.readline

N = int(input())

# schedule[i] = [시작 시간, 종료 시간]
schedule = [list(map(int, input().split())) for _ in range(N)]

# 회의를 종료 시간이 빠른 순서대로 정렬
# 종료 시간 같으면 시작 시간 빠른 순서대로 정렬
schedule.sort(key=lambda x: (x[1], x[0]))

answer = 0      # 선택한 회의 개수
end = 0         # 마지막으로 선택한 회의의 종료 시간

for a, b in schedule:

  # 현재 회의의 시작 시간이 이전 회의의 종료 시간보다 크거나 같으면
  if a >= end:
    answer += 1     # 회의 하나 선택
    end = b         # 종료 시간 업데이트

print(answer)