"""
크루스칼 알고리즘

V: 정점 개수
E: 간선 개수

- 초기화: O(V)
- 입력: O(E)
- 간선 정렬: O(E log E)
- union 처리: O(E log V)
  간선마다 union을 한 번씩 호출하므로 최대 E번
  경로 압축만 적용하면 전체를 합쳐 봤을 때 한 번당 O(log V)
  
E >= V - 1이므로 O(E log V) <= O(E log E)
전체 시간 복잡도: O(E log E)
"""

import sys

input = sys.stdin.readline

V, E = map(int, input().split())

# 처음에는 각 정점이 자기 자신을 부모로 가짐
parent = list(range(V + 1))

def find(x):
    if parent[x] != x:
        parent[x] = find(parent[x])     # 경로 압축: 대표 정점을 바로 가리키도록 갱신
    return parent[x]    # 그룹 대표 정점 반환

def union(a, b):
    root_a = find(a)    # a가 속한 그룹의 대표
    root_b = find(b)    # b가 속한 그룹의 대표

    if root_a == root_b:
        return False    # 이미 연결되어 있어서 이 간선을 추가하면 순환이 생김
    
    parent[root_b] = root_a     # b 그룹을 a 그룹에 합침
    return True

edges = []

for _ in range(E):
    A, B, C = map(int, input().split())
    edges.append((C, A, B))     # 가중치로 정렬하기 위해 C를 맨 앞에 둠

edges.sort()        # 가중치가 작은 간선부터 정렬
mst_weight = 0      # MST의 총 가중치
edge_count = 0      # MST에 포함된 간선 수

for C, A, B in edges:
    if union(A, B):     # 순환이 생기지 않으면 MST에 추가
        mst_weight += C
        edge_count += 1
        if edge_count == V - 1:     # 간선 V - 1개를 모두 고르면 MST 완성
            break

print(mst_weight)



"""
프림 알고리즘

V: 정점 개수
E: 간선 개수

- 초기화: O(V)
- 입력: O(E)
  무방향 그래프라 간선을 양쪽에 저장하므로 인접 리스트 크기는 2E
- 힙 처리: O(E log E)
  각 정점의 인접 간선은 한 번씩만 확인하므로 push는 최대 2E번
  push와 pop은 한 번당 O(log E)

E >= V - 1이므로 O(V) <= O(E log E)
전체 시간 복잡도: O(E log E)
"""

import sys, heapq

input = sys.stdin.readline

V, E = map(int, input().split())

# 정점별 인접 리스트
graph = [[] for _ in range(V + 1)]

# 무방향 그래프라 양쪽 정점에 모두 저장
# 힙에서 가중치 순으로 꺼낼 수 있도록 (가중치, 정점) 순서로 저장
for _ in range(E):
    A, B, C = map(int, input().split())
    graph[A].append((C, B))
    graph[B].append((C, A))

visited = [False] * (V + 1)     # MST에 포함된 정점인지 표시
heap = [(0, 1)]     # 시작점 비용 0으로 1번 정점에서 시작

mst_weight = 0      # MST의 총 가중치
visited_count = 0   # MST에 포함된 정점 수

while heap:
    cost, node = heapq.heappop(heap)    # 가중치가 가장 작은 간선 꺼내기

    if visited[node]:
        continue    # 이미 MST에 있는 정점이면 순환이 생기므로 건너뜀

    visited[node] = True    # MST에 정점 추가
    mst_weight += cost
    visited_count += 1

    if visited_count == V:  # 모든 정점을 포함하면 MST 완성
        break

    for next_cost, next_node in graph[node]:
        if not visited[next_node]:  # 아직 MST에 없는 정점으로 가는 간선만 후보에 추가
            heapq.heappush(heap, (next_cost, next_node))

print(mst_weight)