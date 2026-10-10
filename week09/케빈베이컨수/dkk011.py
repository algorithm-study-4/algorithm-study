"""
O(N + M):
그래프 생성

O((N + M)log N):
우선순위 큐 사용해서 다익스트라 1회 실행

O(N(N + M)log N):
모든 사람을 시작점으로 다익스트라 N번 실행

전체 시간 복잡도는 O(N(N + M)log N)
"""

import sys, heapq

input = sys.stdin.readline

N, M = map(int, input().split())
INF = float('inf')      # 두 사람 사이에 관계가 없을 때 거리

# graph[i]에 i번 사람과 연결된 사람들 저장
graph = [[] for _ in range(N + 1)]

# 친구 관계는 양방향이므로 양쪽에 추가
for _ in range(M):
    A, B = map(int, input().split())
    graph[A].append(B)
    graph[B].append(A)

# start번 사람에서 다른 사람까지의 최단 거리 계산
def dijkstra(start):
    dist = [INF] * (N + 1)
    dist[start] = 0
    pq = [(0, start)]       # (현재 거리, 현재 사람 번호)

    while pq:
        cur_dist, cur = heapq.heappop(pq)

        # 이미 더 짧은 경로가 기록되어 있으면 건너뜀
        if cur_dist > dist[cur]:
            continue

        # 현재 사람하고 연결된 사람들의 최단 거리 갱신
        for nxt in graph[cur]:
            new_dist = cur_dist + 1

            if new_dist < dist[nxt]:
                dist[nxt] = new_dist
                heapq.heappush(pq, (new_dist, nxt))

    return dist

answer = 1
min_sum = INF

# 모든 사람을 시작점으로 다익스트라 알고리즘 수행
for i in range(1, N + 1):
    dist = dijkstra(i)

    # 도달할 수 있는 사람까지의 최단 거리만 더함
    # 관계가 없는 사람은 INF이므로 제외
    total = sum(d for d in dist[1:] if d != INF)

    # 합계가 더 작을 때만 갱신
    # 합계 같으면 기존 번호가 유지돼서 번호가 작은 사람이 선택됨
    if total < min_sum:
        min_sum = total
        answer = i

print(answer)

"""
# O(N^3):
# 플로이드-워셜 알고리즘(3중 for문)

# O(N^2):
# dist 배열 초기화, 거리 합 계산(마지막 for문)

# 전체 시간 복잡도는 O(N^3) -> 시간 초과

import sys

input = sys.stdin.readline

N, M = map(int, input().split())
INF = float('inf')      # 두 사람 사이에 관계가 없을 때 거리

# dist[i][j]는 i번 사람에서 j번 사람까지의 최단 거리
dist = [[INF] * (N + 1) for _ in range(N + 1)]

# 자기 자신과의 거리는 0
for i in range(1, N + 1):
    dist[i][i] = 0

# 친구 관계는 양방향이므로 양쪽 거리 모두 1
for _ in range(M):
    A, B = map(int, input().split())
    dist[A][B] = 1
    dist[B][A] = 1

# k번 사람을 거쳐 가는 경우가 더 짧으면 최단 거리 갱신
for k in range(1, N + 1):
    for i in range(1, N + 1):
        for j in range(1, N + 1):
            dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j])

answer = 1
min_sum = INF
for i in range(1, N + 1):
    # 도달할 수 있는 사람까지의 최단 거리만 더함
    # 관계가 없는 사람은 INF이므로 제외
    total = sum(d for d in dist[i][1:] if d != INF)

    # 합계가 더 작을 때만 갱신
    # 합계 같으면 기존 번호가 유지돼서 번호가 작은 사람이 선택됨
    if total < min_sum:
        min_sum = total
        answer = i

print(answer)
"""