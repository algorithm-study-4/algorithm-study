"""
O(nlogn)
"""

import sys
input = sys.stdin.readline

n = int(input())


times = [list(map(int, input().split())) for _ in range(n)]


times.sort(key= lambda x : x[1])

ans = 0
curr_e = 0

for nxt_s, nxt_e in times:

    if curr_e > nxt_s:
        continue

    curr_e = nxt_e
    ans += 1

print(ans)