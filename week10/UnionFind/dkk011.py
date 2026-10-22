"""
N: 노드 개수
Q: 쿼리 개수

- 초기화: O(N)
- 쿼리 처리: O(Q log N)
  경로 압축만 적용 시 Q번 전체를 합쳐 보면 1번당 O(log N)
  (가끔 find 한 번이 O(N)까지 걸려도 그때 경로가 압축돼서 이후 호출이 빨라짐)

전체 시간 복잡도: O(N + Q log N)
"""

import sys

input = sys.stdin.readline

N, Q = map(int, input().split())

# 각 노드의 부모를 자기 자신으로 초기화
parent = list(range(N + 1))

def find(x):
    if parent[x] != x:
        parent[x] = find(parent[x])     # 경로 압축
    return parent[x]    # 그룹 대표 노드 반환

def union(a, b):
    root_a = find(a)    # A 그룹 대표
    root_b = find(b)    # B 그룹 대표

    if root_a != root_b:
        parent[root_b] = root_a     # 그룹 합치기

for _ in range(Q):
    K, A, B = map(int, input().split())

    if K == 0:
        if find(A) == find(B):
            print("YES")
        else:
            print("NO")
    else:
        union(A, B)