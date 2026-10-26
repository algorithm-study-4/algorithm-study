# 시간복잡도: O(T * N)
# 테스트케이스마다 첫 구간 합 O(W) + 슬라이딩 윈도우 이동 O(N - W) = O(N)

import sys

input = sys.stdin.readline

T = int(input())    # 테스트케이스 수

for i in range(1, T + 1):
    N, W = map(int, input().split())            # 피사체 개수, 사진기 폭
    scores = list(map(int, input().split()))    # 각 피사체 점수

    cur_sum = sum(scores[:W])   # 0번에서 W-1번 인덱스를 촬영했을 때 예술 점수
    max_sum = cur_sum           # 지금까지 최대 예술 점수
    best_start = 0              # 최대 예술 점수가 나오는 구간의 시작 인덱스

    # 사진기를 한 칸씩 오른쪽으로 옮기면서 구간 합 계산
    for j in range(1, N - W + 1):
        # 왼쪽 끝에서 빠지는 피사체 점수는 빼고 오른쪽 끝에 새로 들어오는 피사체 점수는 더함
        cur_sum = cur_sum - scores[j - 1] + scores[j + W - 1]

        # 최대 점수가 여러 곳이면 가장 왼쪽 구간을 출력해야 하므로 >로 비교해서 같을 때는 갱신 안 함
        if cur_sum > max_sum:
            max_sum = cur_sum
            best_start = j

    best_end = best_start + W - 1   # 폭이 W이므로 끝 인덱스는 시작 + W - 1

    print(f"#{i} {best_start} {best_end} {max_sum}")