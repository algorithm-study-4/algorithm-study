# Union find의 기본 개념 <- 크루스칼은 여기에서 확장하면 됨
# Union-Find(Disjoint Set)
# A, B가 같은 그룹인지 빠르게 판단하고, 두 그룹을 합치는 자료구조

# find(x): x가 속한 그룹의 대표(루트)를 반환
# union(a, b): a의 그룹과 b의 그룹을 하나로 합침

# parent[] 배열 하나로 구현하며, 처음에는 모두 자기 자신이 부모 그 자체
# union하면 한쪽 루트를 다른 쪽 루트 밑에 붙인다



# 입력 처리: 첫 줄에서 노드 수 N, 쿼리 수 Q 읽기
N, Q = map(int, input().split())

# parent[i] = i번 노드의 부모 노드
# 처음에는 모든 노드가 부모 노드이므로 parent[i] = i
# 노드 번호가 1~N 까지이므로 인덱스 1부터 시작하도록 설정
parent = [i for i in range(N + 1)]

# find, union 함수 만들기
def find(x):
    if parent[x] == x:
        return x
    # 자기 자신이 루트가 아니라면 진짜 루트 찾기
    # 찾은 루트를 바로 parent 배열에 저장한다 = 경로 압축
    # 다음번 find(x)는 한 번에 루트로 가게 된다
    parent[x] = find(parent[x])
    return parent[x]

def union(a, b):
    # 2개의 각 노드가 속한 그룹의 루트를 구한다
    ra = find(a)
    rb = find(b)

    # 이미 같은 그룹이라면 병합하지 않는다
    if ra == rb:
        return
    # 다른 그룹이라면 b 루트를 a 루트 밑에 붙여서 하나로 만든다
    parent[rb] = ra




# [0, A, B] : 노드 A, B가 서로 같은 그룹 이라면 YES, 아니면 NO 를 출력합니다.
# [1, A, B] : 노드 A, B를 연결합니다

# Q개의 쿼리를 하나씩 처리한다
for _ in range(Q):
    k, a, b = map(int, input().split())

    if k == 1:
        # [1, A, B]: 두 노드를 연결한다
        union(a, b)
    else:
        # [0, A, B]: 같은 그룹인지 확인한다
        # find 로 루트 구해서 비교 진행
        if find(a) == find(b):
            print("YES")
        else:
            print("NO")

