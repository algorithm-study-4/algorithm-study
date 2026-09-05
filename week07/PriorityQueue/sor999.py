"""
O(nlogn)
"""
import heapq
n = int(input())

pq = []
ans = []
for _ in range(n):
    command, num = input().split()
    num = int(num)

    if command == 'push':
        heapq.heappush(pq, -num)

    elif command == 'pop':
        temp = []
        for _ in range(num):
            curr = -heapq.heappop(pq)
            temp.append(curr)
        ans.append(" ".join(map(str, temp)))
    else:
        curr = -heapq.heappop(pq)
        curr += num
        heapq.heappush(pq, -curr)

print("\n".join(ans))