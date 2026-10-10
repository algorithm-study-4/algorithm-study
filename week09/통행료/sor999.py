import heapq

INF = float('inf')

n, m, k = map(int, input().split())
a, b = map(int, input().split())

g = [[] for _ in range(n + 1)]

for _ in range(m):
    x, y, w = map(int, input().split())
    g[x].append((y, w))
    g[y].append((x, w))

taxes = [int(input()) for _ in range(k)]


dist = [[INF] * n for _ in range(n + 1)]
dist[a][0] = 0

pq = [(0, a, 0)]
min_edges = [n] * (n + 1)

while pq:
    w, curr, cnt = heapq.heappop(pq)

    if w != dist[curr][cnt]:
        continue

    if cnt >= min_edges[curr]:
        continue

    min_edges[curr] = cnt

    if curr == b:
        continue

    for nxt, cost in g[curr]:
        nw = w + cost
        nc = cnt + 1

        if nc >= n or nc >= min_edges[nxt]:
            continue

        if nw < dist[nxt][nc]:
            dist[nxt][nc] = nw
            heapq.heappush(pq, (nw, nxt, nc))

tax = 0
ans = []

for i in range(k + 1):
    min_cost = INF

    for cnt in range(1, n):

        if dist[b][cnt] == INF:
            continue

        min_cost = min(min_cost, dist[b][cnt] + tax * cnt)

    ans.append(str(min_cost))

    if i < k:
        tax += taxes[i]

print('\n'.join(ans))