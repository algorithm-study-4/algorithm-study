"""
방법1. 이분탐색
O(nlogn + klogn) = O(nlogn)
"""

n = int(input())
arr = list(map(int, input().split()))
k = int(input())
queries = list(map(int, input().split()))

def is_exist(target):
    s, e = 0, n - 1

    while s <= e:
        mid = (s + e) // 2

        if arr[mid] < target:
            s = mid + 1
        elif arr[mid] > target:
            e = mid - 1
        else:
            return True
    return False

ans = []

arr.sort()

for query in queries:
    ans.append('O' if is_exist(query) else 'X')


print("".join(map(str, ans)))


"""
방법2. 완전탐색
O(n^2)
"""

n = int(input())
arr = list(map(int, input().split()))
k = int(input())
queries = list(map(int, input().split()))

def is_exist(target):
    for curr in arr:
        if target == curr:
            return True
    return False


ans = []

for query in queries:
    ans.append('O' if is_exist(query) else 'X')


print("".join(map(str, ans)))