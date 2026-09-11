
"""
O(nlogn)
"""
import heapq
n = int(input())

pq = []
ans = []

for _ in range(n):
    query = int(input())

    if query == 0:
        if pq:
            num, sign = heapq.heappop(pq)
            ans.append(num * sign)
        else:
            ans.append(0)
    else:

        sign = -1 if query < 0 else 1

        curr = (abs(query), sign)

        heapq.heappush(pq, curr)

print("\n".join(map(str, ans)))