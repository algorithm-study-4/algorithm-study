# 시간복잡도: O(N^2)
# 문자 N개를 하나씩 고르고 고를 때마다 양 끝이 같으면 안쪽으로 최대 N번 비교

import sys

input = sys.stdin.readline

N = int(input())
S = [input().rstrip() for _ in range(N)]
answer = []                 # 만들어지는 문자열 T
left, right = 0, N - 1      # 아직 사용하지 않은 구간의 양 끝 포인터

while left <= right:
    i, j = left, right

    # 양 끝이 같으면 안쪽으로 들어가면서 비교
    # 더 작은 문자가 있는 쪽을 먼저 선택해야 T가 사전순으로 더 빨라짐
    while i < j and S[i] == S[j]:
        i += 1
        j -= 1

    # 왼쪽 문자가 더 작거나 끝까지 같으면 왼쪽 선택
    if S[i] <= S[j]:
        answer.append(S[left])
        left += 1
    # 오른쪽 문자가 더 작으면 오른쪽 선택
    else:
        answer.append(S[right])
        right -= 1

# 80글자마다 줄바꿈
for k in range(0, N, 80):
    print(''.join(answer[k:k + 80]))