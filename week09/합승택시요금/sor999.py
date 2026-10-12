"""
출발지 s에서 A와 B가 각각 목적지 a, b까지 이동할 때, 합승을 적절히 활용하여 총 택시 요금의 최솟값

알고리즘:
- 모든 지점 c에 대해 s -> c까지 합승하고 c -> a, c -> b로 따로 이동하는 비용을 계산
- 최소 비용을 계산

시간복잡도: O((n + m)logn)
"""
import heapq
def solution(n, s, a, b, fares):
    INF = float('inf')
    
    g = [[] for _ in range(n + 1)]

    for x, y, w in fares:
        g[x].append((y, w))
        g[y].append((x, w))

    # 다익스트라로 start에서의 최단 거리 구하기
    def dij(start):
        dist = [INF] * (n + 1)
        dist[start] = 0

        pq = [(0, start)]

        while pq:
            w, curr = heapq.heappop(pq)

            # 최소값이 될 수 없는 경로는 가지 않음
            if dist[curr] < w:
                continue

            for nxt, cost in g[curr]:
                nw = w + cost

                if nw < dist[nxt]:
                    dist[nxt] = nw
                    heapq.heappush(pq, (nw, nxt))

        return dist

    # 각 지점에서 모든 지점까지의 최단 거리 계산
    start_a = dij(a)
    start_b = dij(b)
    start_s = dij(s)

    # 합승하지 않고 각자 이동하거나
    # s -> a -> b 또는 s -> b -> a로 이동하는 경우
    ans = min(start_s[a] + start_a[b], start_s[b] + start_b[a], start_s[a] + start_s[b])

    # 임의의 지점 c까지 합승한 후 각자 목적지로 이동
    # s -> c (합승) / c -> a (A 이동) / c -> b (B 이동)
    for c in range(1, n + 1):
        cost = start_s[c] + start_a[c] + start_b[c]
        ans = min(ans, cost)

    return ans