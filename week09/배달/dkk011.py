"""
O(N + M):
그래프 생성

O((N + M) log N):
우선순위 큐 사용해서 다익스트라 실행

O(N):
K 시간 이내에 배달 가능한 마을 수 계산

전체 시간 복잡도는 O((N + M)log N)
"""

import heapq

def solution(N, road, K):
    # graph[i]에 i번 마을과 연결된 마을 및 배달 시간 저장
    graph = [[] for _ in range(N + 1)]

    # 도로는 양방향이므로 양쪽에 모두 저장
    for v1, v2, t in road:
        graph[v1].append((v2, t))
        graph[v2].append((v1, t))

    # min_t[i]는 1번 마을에서 i번 마을까지의 최소 배달 시간
    min_t = [float('inf')] * (N + 1)
    min_t[1] = 0

    # 우선순위 큐에 (현재까지의 배달 시간, 마을 번호) 형태로 저장
    pq = [(0, 1)]

    while pq:
        cur_t, cur_v = heapq.heappop(pq)

        # 이미 더 짧은 배달 시간이 기록되어 있으면 건너뜀        
        if cur_t > min_t[cur_v]:
            continue

        # 현재 마을과 연결된 마을들의 최소 배달 시간 갱신
        for nxt_v, delivery_t in graph[cur_v]:
            new_t = cur_t + delivery_t
            
            if new_t < min_t[nxt_v]:
                min_t[nxt_v] = new_t
                heapq.heappush(pq, (new_t, nxt_v))

    # K 시간 이내에 배달 가능한 마을 수 계산
    return sum(1 for v in range(1, N + 1) if min_t[v] <= K)