# 도시 A 에서 B까지 가는 최소 통행료를 구한다
# 양방향 도로, 같은 두 도시 사이 도로가 여러 개일 수 있음
# 매년 통행료가 오름

# 입력
# 도시 수 N, 도로 수 M
# 도시 A, 도시 B

# M개의 도로 정보 f, t, c
# K개의 정수 p, 1~K년 후 인상되는 금액

# 플로이드 - 한 번만 해도 10억임, 거리표 재활용이 불가능해서 11번 해야 함
# 다익스트라 - 11번 해도 340만번에 끝낼 수 있음

import sys
import heapq
input = sys.stdin.readline

# 1. 입력 받기
N, M, K = map(int, input().split())
A, B = map(int, input().split())

graph = [[] for _ in range(N + 1)]
for _ in range(M):
    f, t, c = map(int, input().split())
    graph[f].append((t, c))
    graph[t].append((f, c))


# 2. 해마다 통행료 오르는 누적 인상액
# extras[y] = y년 후 모든 도로에 붙는 추가 금액
# 누적 인상이므로 p를 계속 더해나가기

extras = [0]
total = 0
for _ in range(K):
    p = int(input())
    total += p
    extras.append(total)


# 3. 다익스트라 : 도로 한 개 지날 때마다 기본 통행료 + extra 지불
# A 에서 B까지 최소 통행료 반환

def dijkstra(extra):
    INF = float('inf')
    dist = [INF] * (N + 1)
    dist[A] = 0
    heap = [(0, A)]

    while heap:
        cur_dist, cur = heapq.heappop(heap) # 지금 가장 싼 도시 꺼내기
        if cur_dist > dist[cur]:
            continue
        if cur == B:
            return cur_dist
        for nxt, c in graph[cur]:
            nd = cur_dist + c + extra         # 인상된 통행료로 cur를 거쳐 nxt로 가는 비용
            if nd < dist[nxt]:                # 지금까지 알던 것보다 싸면
                dist[nxt] = nd                # 갱신
                heapq.heappush(heap, (nd, nxt))
    return dist[B]

# 4단계
for extra in extras:
    print(dijkstra(extra))