import sys
input = sys.stdin.readline
# 입력 속도를 높이기 위해 input 함수를 교체... 하는 방법이 있대서 추가해봤어요 (파이썬)

n = int(input())
# 회의 개수 입력

meetings = []
# 회의 정보를 저장할 리스트 만들기

for _ in range(n):
    a, b = map(int, input().split())
    # 시작 시각과 종료 시각 입력받기
    meetings.append((b, a))
    # (종료 시각, 시작 시각) 순서로 리스트에 추가 (종료 시각 기준으로 정렬해야 함)

meetings.sort()
# 종료 시각 기준(같으면 시작 시각 기준)으로 오름차순 정렬하기

count = 0
# 선택된 회의 개수 count 만들기

last_end = 0
# 마지막으로 선택된 회의의 종료 시간을 저장

for end, start in meetings:
    # 정렬된 순서대로 (종료시각, 시작시각) 꺼내기

    if start >= last_end:
        # 이전 회의와 겹치지 않으면

        count += 1
        # 회의 선택

        last_end = end
        # 종료 시간 갱신

print(count)
# 최대 회의 개수 출력