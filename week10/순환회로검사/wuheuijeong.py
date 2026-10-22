# 인접행렬로 주어진 무방향 그래프에 사이클이 존재하는지 확인하는 문제
# 입력받은 데이터에 순환회로가 존재한다면 "WARNING"을, 순환회로가 존재하지 않다면 "STABLE" 을 출력하세요.

# 노드 A ~ E는 인덱스 0~4로 생각한다

# 노드 개수 N 입력
N = int(input())

# 인접행렬 입력: N줄을 읽어서 2차원 리스트로 저장한다
# matrix[i][j] == 1 이면 i번 노드와 j번 노드가 연결된 것
matrix = [list(map(int, input().split())) for _ in range(N)]

parent = [i for i in range(N)]

def find(x):
    if parent[x] == x:
        return x
    parent[x] = find(parent[x])
    return parent[x]

def union(a, b):
    ra, rb = find(a), find(b)
    if ra != rb:
        parent[rb] = ra

def is_cycle():
    # i < j 인 칸만 순회해야 함 : 왜냐하면 matrix[i][j] == matrix[j][i]는 대칭이라 사이클이 아닌데 워닝 뜰 수 있음
    for i in range(N):
        for j in range(i+1, N):
            # 간선이 없으면 건너뛴다
            if matrix[i][j] == 0:
                continue

            # 간선 추가 전에 이미 같은 그룹인지 확인
            # 같은 그룹인데 간선을 놓으면 사이클이 생간다
            if find(i) == find(j):
                return True
            union(i, j)
    return False


if is_cycle():
    print("WARNING")
else:
    print("STABLE")