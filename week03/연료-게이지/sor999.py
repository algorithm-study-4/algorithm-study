"""
총 연로가 몇% 채워졌는지 출력
O(nlogm)
"""
n = int(input())

ans = []

for _ in range(n):
    strs = input()
    m = len(strs)
    s, e = 0, len(strs) - 1
    while s <= e:
        mid = (s + e) // 2

        if strs[mid] == '#':
            s = mid + 1
        else:
            e = mid - 1

    ans.append(str((e + 1) * 100 // m) + "%")

print("\n".join(ans))
