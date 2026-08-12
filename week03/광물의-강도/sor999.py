"""
연구원이 원하는 강도의 광물을 몇 개 소유하고 있는지 출력

1. 직접 upper, lower bound 구현
O(nlogn + qlogn)
"""
import sys

def get_lower(target):
    s, e = 0, n - 1

    while s <= e:
        mid = (s + e) // 2

        if arr[mid] < target:
            s = mid + 1
        else:
            e = mid - 1
    return s

def get_upper(target):
    s, e = 0, n - 1

    while s <= e:
        mid = (s + e) // 2

        if arr[mid] <= target:
            s = mid + 1
        else:
            e = mid - 1
    return s

input = sys.stdin.readline
n, q = map(int, input().split())
arr = list(map(int, input().split()))

arr.sort()

ans = []
for _ in range(q):
    s, e = map(int, input().split())

    lower = get_lower(s)
    upper = get_upper(e)

    ans.append(upper - lower)

print("\n".join(map(str, ans)))


"""
2. 라이브러리 사용
O(nlogn + qlogn)
"""
import sys
from bisect import bisect_left, bisect_right
input = sys.stdin.readline
n, q = map(int, input().split())
arr = list(map(int, input().split()))

arr.sort()

ans = []
for _ in range(q):
    s, e = map(int, input().split())

    lower = bisect_left(arr, s)
    upper = bisect_right(arr, e)

    ans.append(upper - lower)

print("\n".join(map(str, ans)))