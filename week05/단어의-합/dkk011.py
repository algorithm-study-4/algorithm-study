# O(N * L + K log K)
import sys

input = sys.stdin.readline

N = int(input().rstrip())
words = [input().rstrip() for _ in range(N)]

# 각 알파벳이 전체 단어에서 가지는 가중치 합 저장
weights = {}
for word in words:

    # 단어를 일의 자리부터 확인하여 알파벳의 가중치 계산
    for i, idx in enumerate(reversed(word)):
        # ABC인 경우, weights[A] = 100, weights[B] = 10, weights[C] = 1
        weights[idx] = weights.get(idx, 0) + 10 ** i

# 알파벳별 가중치를 큰 순서대로 정렬 (가중치만 저장)
weights = sorted(weights.values(), reverse=True)

# 가중치가 큰 알파벳부터 큰 숫자를 배정한다고 생각하고
# 9, 8, 7, ... 순서로 곱해 최댓값 계산
answer = sum(weight * (9 - i) for i, weight in enumerate(weights))

print(answer)