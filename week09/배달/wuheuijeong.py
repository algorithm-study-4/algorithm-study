# 1번 마을 한 곳에서 나머지까지만 필요함, 다익스트라 알고리즘 채택
# 플로이드는 모든 쌍을 구해야 하기 때문에 과함
# N개의 마을이 있고, K시간 이하로 배달 가능한 마을의 개수를 구하는 문제

import heapq

def solution(N, road, K):
    INF = float('inf')

    # 1. 인접 리스트: graph[x] = [(이웃 마을, 걸리는 시간)]
    graph = [[] for _ in range(N + 1)]
    for a, b, c in road:
        graph[a].append((b, c))
        graph[b].append((a, c))  # 양방향


    # 2. 거리표
    dist = [INF] * (N + 1)
    dist[1] = 0  # 시작 마을 1의 거리는 0
    heap = [(0, 1)] # (거리, 마을)

    # 3. 다익스트라
    while heap:
        cur_dist, cur = heapq.heappop(heap)
        if cur_dist > dist[cur]: # 더 짧은 경로를 발견한 경우
            continue
        for nxt, w in graph[cur]: # cur의 이웃 마을 확인하기
            nd = cur_dist + w
            if nd < dist[nxt]: # 더 짧은 경로를 발견한 경우
                dist[nxt] = nd # 갱신하기
                heapq.heappush(heap, (nd, nxt)) # 이 마을 이웃도 확인하도록 힙에 넣기

    return sum(1 for d in dist if d <= K) # K 이하인 마을 개수 세기

    