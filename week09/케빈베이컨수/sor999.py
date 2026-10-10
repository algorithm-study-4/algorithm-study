from collections import deque, defaultdict

n, m = map(int, input().split())


g = defaultdict(list)


for _ in range(m):
    a, b = map(int, input().split())
    g[a].append(b)
    g[b].append(a)


INF = float('inf')
def bfs(s):
    dist = [INF] * (n + 1)
    dist[s] = 0

    q = deque()
    q.append(s)

    while q:
        curr = q.popleft()

        for nxt in g[curr]:
            if dist[nxt] == INF:
                dist[nxt] = dist[curr] + 1
                q.append(nxt)
    
    total = 0
    for i in range(1, n + 1):
        if dist[i] == INF:
            continue
        total += dist[i]
    return total

ans = 0
min_v = INF
for i in range(1, n + 1):
    cnt = bfs(i)

    if min_v > cnt:
        min_v = cnt
        ans = i

print(ans)        
