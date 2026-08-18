# O(n log n) + O(k log n) → O((n + k) log n)
import sys

input = sys.stdin.readline

n = int(input())
array_n = list(map(int, input().split()))

k = int(input())
array_k = list(map(int, input().split()))

# 이진 탐색을 위해 오름차순으로 정렬
array_n.sort()      # O(n log n)

# 탐색 결과를 저장할 리스트
answer = []

for i in array_k:   # O(k)
    left = 0
    right = len(array_n) - 1

    # 값을 찾았는지 여부 저장
    found = False

    while left <= right:    #O(log n)
        # 현재 탐색 범위의 중간 인덱스
        mid = (left + right) // 2

        # 찾는 값이 중간값과 같으면 탐색 종료
        if i == array_n[mid]:
            found = True
            break

        # 찾는 값이 더 크면 오른쪽 절반 탐색
        if i > array_n[mid]:
            left = mid + 1

        # 찾는 값이 더 작으면 왼쪽 절반 탐색
        elif i < array_n[mid]:
            right = mid - 1

    if found:
        answer.append("O")
    else:
        answer.append("X")

print("".join(answer))
