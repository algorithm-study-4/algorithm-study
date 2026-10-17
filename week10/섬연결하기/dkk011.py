"""
크루스칼 알고리즘

n: 섬 개수
E: 다리 후보 개수 (costs의 길이)

- 초기화: O(n)
- 간선 정렬: O(E log E)
- union 처리: O(E log n)
  간선마다 union을 한 번씩 호출하므로 최대 E번
  경로 압축만 적용하면 전체를 합쳐 봤을 때 한 번당 O(log n)

E >= n - 1이므로 O(E log n) <= O(E log E)
전체 시간 복잡도: O(E log E)
"""

def solution(n, costs):
    # 처음에는 각 섬이 자기 자신을 부모로 가짐
    parent = list(range(n))

    def find(x):
        if parent[x] != x:
            parent[x] = find(parent[x])     # 경로 압축: 대표 섬을 바로 가리키도록 갱신
        return parent[x]    # 그룹 대표 섬 반환

    def union(a, b):
        root_a = find(a)    # a가 속한 그룹의 대표
        root_b = find(b)    # b가 속한 그룹의 대표

        if root_a == root_b:
            return False    # 이미 연결되어 있어서 이 다리를 추가하면 순환이 생김

        parent[root_b] = root_a     # b 그룹을 a 그룹에 합침
        return True

    costs.sort(key=lambda x: x[2])  # 비용이 작은 다리부터 정렬

    mst_weight = 0      # MST의 총 비용
    edge_count = 0      # MST에 포함된 다리 수

    for A, B, C in costs:
        if union(A, B):     # 순환이 생기지 않으면 MST에 추가
            mst_weight += C
            edge_count += 1
            if edge_count == n - 1:     # 다리 n - 1개를 모두 고르면 MST 완성
                break

    return mst_weight