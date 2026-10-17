"""
BFS + 크루스칼 알고리즘

X, Y: 맵의 가로, 세로 크기
n: 노드 개수 (S와 A를 합친 수)

- 입력: O(XY)
- 간선 생성: O(n * XY)
  노드마다 BFS를 한 번씩 돌리고 BFS 한 번에 맵 전체를 탐색
  간선은 노드 쌍마다 하나씩 생기므로 n(n - 1) / 2개
- 간선 정렬: O(n^2 log n)
- union 처리: O(n^2 log n)
  간선마다 union을 한 번씩 호출하므로 최대 n^2번
  경로 압축만 적용하면 전체를 합쳐 봤을 때 한 번당 O(log n)

테스트 케이스당 전체 시간 복잡도: O(n * XY + n^2 log n)
"""

import sys
from collections import deque

input = sys.stdin.readline

# 상하좌우 이동
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

def bfs(sy, sx):
    dist = [[-1] * X for _ in range(Y)]     # 아직 방문하지 않은 칸은 -1
    dist[sy][sx] = 0
    queue = deque([(sy, sx)])

    while queue:
        y, x = queue.popleft()

        for i in range(4):
            ny, nx = y + dy[i], x + dx[i]

            # 맵 안이고, 벽이 아니고, 아직 방문하지 않은 칸만 이동
            if 0 <= ny < Y and 0 <= nx < X and board[ny][nx] != "#" and dist[ny][nx] == -1:
                dist[ny][nx] = dist[y][x] + 1
                queue.append((ny, nx))

    return dist     # 출발점에서 각 칸까지의 최단 거리

def find(x):
    if parent[x] != x:
        parent[x] = find(parent[x])     # 경로 압축: 대표 노드를 바로 가리키도록 갱신
    return parent[x]    # 그룹 대표 노드 반환

def union(a, b):
    root_a = find(a)    # a가 속한 그룹의 대표
    root_b = find(b)    # b가 속한 그룹의 대표

    if root_a == root_b:
        return False    # 이미 연결되어 있어서 이 간선을 추가하면 순환이 생김

    parent[root_b] = root_a     # b 그룹을 a 그룹에 합침
    return True

T = int(input())

for _ in range(T):
    X, Y = map(int, input().split())
    board = []
    nodes = []      # S와 A의 좌표를 모아 두고 인덱스를 노드 번호로 사용

    for r in range(Y):
        row = input().rstrip()
        board.append(row)

        for c in range(X):
            if row[c] in "SA":
                nodes.append((r, c))

    n = len(nodes)
    edges = []

    # 노드마다 BFS를 돌려 다른 노드까지의 거리를 간선 비용으로 저장
    for i in range(n - 1):  # 마지막 노드는 더 연결할 상대가 없으므로 제외
        sy, sx = nodes[i]
        dist = bfs(sy, sx)

        for j in range(i + 1, n):   # 같은 간선이 두 번 저장되지 않도록 i 다음 노드부터 확인
            ty, tx = nodes[j]
            edges.append((dist[ty][tx], i, j))

    parent = list(range(n))     # 테스트 케이스마다 새로 초기화

    edges.sort()        # 비용이 작은 간선부터 정렬
    mst_weight = 0      # MST의 총 비용
    edge_count = 0      # MST에 포함된 간선 수

    for cost, a, b in edges:
        if union(a, b):     # 순환이 생기지 않으면 MST에 추가
            mst_weight += cost
            edge_count += 1
            if edge_count == n - 1:     # 간선 n - 1개를 모두 고르면 MST 완성
                break

    print(mst_weight)