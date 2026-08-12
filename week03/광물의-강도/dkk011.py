# O((n + Q) log n)
import sys

input = sys.stdin.readline

N, Q = map(int, input().split())
minerals = list(map(int, input().split()))

# O(n log n)
minerals.sort()

# O(log n) * Q → O(Q log n)
for _ in range(Q):
    S, E = map(int, input().split())

    # S 이상의 강도가 처음 나오는 위치 찾기
    left = 0
    right = N

    # O(log n)
    while left < right:
        mid = (left + right) // 2
        if minerals[mid] < S:
            left = mid + 1      # S보다 작으면 조건을 만족하지 않으므로 오른쪽 탐색
        else:
            right = mid         # S 이상이면 더 앞에도 있을 수 있으므로 왼쪽 탐색

    lower = left

    # E를 초과하는 강도가 처음 나오는 위치 찾기
    left = 0
    right = N

    # O(log n)
    while left < right:
        mid = (left + right) // 2
        if minerals[mid] <= E:
            left = mid + 1      # 아직 E를 초과하지 않았으므로 오른쪽 탐색
        else:
            right = mid         # E보다 크면 더 앞에도 있을 수 있으므로 왼쪽 탐색

    upper = left

    print(upper - lower)