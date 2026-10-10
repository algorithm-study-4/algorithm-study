import sys
input = sys.stdin.readline

# 1. 입력 받기
N, M = map(int, input().split()) # N: 사람 수, M: 친구 관계 수

INF = 10 ** 9 # 연결 안 됨 무한대 설정 -> float('inf') 보다 속도가 빠름

# 2. 거리표 만들기
# dist[a][b] = a에서 b까지 현재까지의 최단거리
dist = [[INF] * (N + 1) for _ in range(N + 1)]

for i in range(1, N+1):
    dist[i][i] = 0 # 자기 자신과의 거리는 0으로

for _ in range(M):
    a, b = map(int, input().split()) # 친구 관계 입력 받기
    dist[a][b] = 1
    dist[b][a] = 1 # 친구는 양방향이라 2개에 모두 입력해줘야 함

# 3. 플로이드 워셜 알고리즘 사용
for k in range(1, N + 1): # k는 경유지
    dk = dist[k] # k번 행을 미리 꺼내둠
    for i in range(1, N + 1):
        dik = dist[i][k] # i -> k 의 거리
        if dik == INF:
            continue
        di = dist[i] # i번 행을 미리 꺼내둠
        for j in range(1, N + 1):
            nd = dik + dk[j] # i -> k -> j 거리
            if nd < di[j]: # 지금까지 알던 i -> j 거리보다 작으면
                di[j] = nd # 갱신한다

# 4. 케빈 베이컨 수가 가장 작은 사람 찾기
best = 0
best_sum = INF

for i in range(1, N + 1): # 1번부터 차례대로 보기
    s = sum(x for x in dist[i][1:] if x < INF) # i번이 모두에게 가는 거리의 합 (0번 제외), INF는 제외해야 함 (처음 제출 시 틀린 부분)
    if s < best_sum: # < 사용해서 같은 경우는 갱신 안 함
        best_sum = s
        best = i

print(best)


# 예제
# 입력: N=5, M=5
# 1-3, 1-4, 2-3, 3-4, 4-5

# ① 2단계 직후의 거리표 (친구 관계만 입력된 상태)
#        1  2  3  4  5
#   1 [  0  ∞  1  1  ∞ ]
#   2 [  ∞  0  1  ∞  ∞ ]
#   3 [  1  1  0  1  ∞ ]
#   4 [  1  ∞  1  0  1 ]
#   5 [  ∞  ∞  ∞  1  0 ]
#
# ② 3단계에서 거리표가 바뀌는 순간 (바뀌지 않는 k는 생략)
#   k=3: 1→3→2 = 2  → dist[1][2] = 2  (dist[2][1]도 2)
#        2→3→4 = 2  → dist[2][4] = 2  (dist[4][2]도 2)
#   k=4: 1→4→5 = 2  → dist[1][5] = 2
#        3→4→5 = 2  → dist[3][5] = 2
#        2→4→5 = 3  → dist[2][5] = 3  (k=3에서 줄어든 2→4 결과를 이어서 사용)
#   k=1, 2, 5: 더 짧아지는 경로 없음
#
# ③ 3단계 종료 후 최종 거리표 / 각 줄의 합
#        1  2  3  4  5   합(케빈 베이컨 수)
#   1 [  0  2  1  1  2 ]   6
#   2 [  2  0  1  2  3 ]   8
#   3 [  1  1  0  1  2 ]   5   ← 최소 (4번과 동점이지만 번호가 작아 선택)
#   4 [  1  2  1  0  1 ]   5
#   5 [  2  3  2  1  0 ]   8
#
# ④ 4단계 결과: 출력 = 3


# 이렇게 플로이드 알고리즘 사용 시 시간 초과되어 탈락함 (파이썬에서 플로이드 쓰는 경우 6,400만 번 계산하게 되어 걸림)

# => 해결 방법: 플로이드 대신 BFS 사용하기
# 시작점부터 가까운 사람부터 차례대로 방문함, 거리 1인 사람 다 방문하고 거리 2인 사람 방문...
# 모든 사람을 시작점으로 N번 돌리면 됨

import sys
from collections import deque
input = sys.stdin.readline

# 1. 입력 받기
N, M = map(int, input().split()) # N: 사람 수, M: 친구 관계 수
graph = [[] for _ in range(N + 1)] # 0번 칸 X

for _ in range(M):
    a, b = map(int, input().split())
    graph[a].append(b)
    graph[b].append(a) # 친구 관계는 양방향

# 2. 모든 사람을 시작점으로 BFS 실행
best_person = 0
best_sum = float('inf')

for start in range(1, N + 1):
    dist = [-1] * (N + 1) # -1: 아직 방문 안 함
    dist[start] = 0 # 시작점은 거리 0
    queue = deque([start])
    total = 0 # start의 케빈 베이컨 수

    while queue:
        cur = queue.popleft() # 가장 먼저 들어온 사람 꺼내기
        for nxt in graph[cur]: # cur의 친구들 확인
            if dist[nxt] == -1: # 처음 만나는 사람들이면
                dist[nxt] = dist[cur] + 1 # 거리 갱신 = 지금 사람의 거리 + 1

                total += dist[nxt] # 케빈 베이컨 수에 더하기
                queue.append(nxt) # 이 사람 친구들도 이어서 확인하기

    # 3. 가장 작은 케빈 베이컨 수 갱신
    if total < best_sum: # 같은 경우는 갱신 X
        best_sum = total # 동점이면 번호 작은 사람 유지하기
        best_person = start

print(best_person)