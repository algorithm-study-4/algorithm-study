"""
N: 노드 개수

- 입력: O(N^2)
  N x N 인접행렬 전체 읽기
- 간선 확인: O(N^2)
  행렬에서 대각선 위쪽 절반(i < j)만 확인 -> 약 N^2/2칸
- union 처리: O(N log N)
  합치기는 최대 N - 1번 성공하고 실패하는 순간 바로 종료하므로 union 호출은 최대 N번
  경로 압축만 적용 시 전체를 합쳐 보면 한 번당 O(log N)

전체 시간 복잡도: O(N^2)
"""

import sys

input = sys.stdin.readline

N = int(input())
graph = [list(map(int, input().split())) for _ in range(N)]     # 인접행렬 입력

# 각 노드의 부모를 자기 자신으로 초기화
parent = list(range(N))

def find(x):
    if parent[x] != x:
        parent[x] = find(parent[x])     # 경로 압축
    return parent[x]    # 그룹 대표 노드 반환

def union(a, b):
    root_a = find(a)    # A 그룹 대표
    root_b = find(b)    # B 그룹 대표

    if root_a == root_b:
        return False    # 이미 같은 그룹 -> 이 간선 이으면 순환 만들어짐
    
    parent[root_b] = root_a     # 그룹 합치기
    return True

def has_cycle():
    for i in range(N):
        for j in range(i + 1, N):   # 무방향 그래프라 대각선 위쪽만 확인 (중복 간선 방지)
            if graph[i][j] == 1:    # 간선이 있는 경우만
                if not union(i, j):
                    return True     # 순환 발견하면 바로 종료
    return False    # 모든 간선 확인 후에도 순환 없음

print("WARNING" if has_cycle() else "STABLE")