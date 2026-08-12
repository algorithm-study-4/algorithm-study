"""
import sys

input = sys.stdin.readline

N = int(input())

for _ in range(N):
    s = input().rstrip()
    percent = s.count('#') * 100 // len(s)
    print(f"{percent}%")
"""

# O(n log n)
import sys

input = sys.stdin.readline

N = int(input())

# O(n)
for _ in range(N):
    state = input().rstrip()

    left = 0                # left: 탐색 시작 위치
    right = len(state) - 1  # right: 탐색 끝 위치

    # O(log n)
    while left <= right:
        mid = (left + right) // 2

        # '#'이 아직 오른쪽에 더 있을 수 있으므로 왼쪽 범위 증가
        if state[mid] == '#':
            left = mid + 1

        # '#'이 더 왼쪽에 있으므로 오른쪽 범위 줄임
        else:
            right = mid - 1

    # left는 '#'의 개수이므로 전체 길이로 나누어 백분율 계산
    percent = left * 100 // len(state)
    print(f"{percent}%")