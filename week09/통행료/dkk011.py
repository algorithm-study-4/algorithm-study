"""
O(N + M):
그래프 생성

O((N + M) log N):
다익스트라 1회 실행

O(K(N + M) log N):
매년 누적된 통행료 인상액 반영해서 다익스트라 K번 실행

전체 시간 복잡도는 바로 위 두 경우를 합한 O((K + 1)(N + M) log N)

플로이드-워셜 알고리즘은 O(K * N^3)으로 시간 초과 발생 가능성이 높다.
이 문제처럼 특정 출발점에서 특정 도착점까지의 최단 경로를
여러 번 구해야 하는 경우에서는 다익스트라 알고리즘을 사용하는 것이 더 효율적이다.
"""

import sys, heapq

input = sys.stdin.readline

# N: 도시 수, M: 고속도로 수, K: 해 수
N, M, K = map(int, input().split())

# A: 출발 도시, B: 도착 도시
A, B = map(int, input().split())

# roads[i]에 i번 도시와 연결된 도시 및 통행료를 튜플 형태로 저장
roads = [[] for _ in range(N + 1)]

# 도로는 양방향이므로 양쪽 도시에 모두 저장
for _ in range(M):
    f, t, c = map(int, input().split())
    roads[f].append((t, c))
    roads[t].append((f, c))

# 매년 인상되는 통행료 금액 저장
increases = [int(input()) for _ in range(K)]

# 누적 통행료 인상액 increase를 반영한 최소 통행료 계산
def dijkstra(increase):
    INF = float('inf')

    # min_cost[i]는 A에서 i번 도시까지의 최소 통행료
    min_cost = [INF] * (N + 1)
    min_cost[A] = 0

    # 우선순위 큐 pq에 (현재까지의 통행료, 도시 번호) 저장
    pq = [(0, A)]

    while pq:
        cur_cost, cur_city = heapq.heappop(pq)

        # 더 적은 통행료가 이미 저장돼 있으면 건너뛰기
        if cur_cost > min_cost[cur_city]:
            continue

        # B에 도달하면 최소 통행료 반환
        if cur_city == B:
            return cur_cost

        # 현재 도시와 연결된 도로를 이용해서 최소 통행료 갱신
        for nxt_city, toll in roads[cur_city]:
            # 현재까지의 통행료에 도로 통행료와 누적 인상액 더하기
            new_cost = cur_cost + toll + increase

            if new_cost < min_cost[nxt_city]:
                min_cost[nxt_city] = new_cost
                heapq.heappush(pq, (new_cost, nxt_city))

    return min_cost[B]

# 첫 해의 최소 통행료 출력
print(dijkstra(0))

total_increase = 0
for i in increases:
    total_increase += i

    # 누적 통행료 인상액을 반영한 해당 연도의 최소 통행료 출력
    print(dijkstra(total_increase))
