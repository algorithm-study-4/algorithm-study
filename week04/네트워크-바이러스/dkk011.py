import sys
from collections import defaultdict, deque

input = sys.stdin.readline

N = int(input())    # 컴퓨터 수
T = int(input())    # 네트워크에 연결된 컴퓨터 쌍의 수

answer = 0          # 1번 컴퓨터를 통해 감염되는 컴퓨터 수
visited = [False] * (N + 1)     # 컴퓨터 방문 여부 저장
computers = defaultdict(list)   # computers[i]에는 i번 컴퓨터와 연결된 컴퓨터 번호 저장

for _ in range(T):
    i, j = map(int, input().split())

    # i와 j가 서로 연결되어 있으므로 양쪽에 모두 추가
    computers[i].append(j)
    computers[j].append(i)

# BFS
# visited가 True가 되면 다시 큐에 들어가지 않으므로 최대 N번 방문
# 큐에 들어가는 횟수는 최대 T번이므로 O(N + T)
queue = deque()
queue.append(1)     # 1번 컴퓨터부터 시작
visited[1] = True   # 1번 컴퓨터 방문 처리

while queue:
    current = queue.popleft()
    for i in computers[current]:    # 현재 컴퓨터와 연결된 모든 컴퓨터 확인
        if not visited[i]:          # 방문하지 않은 컴퓨터라면
            visited[i] = True       # 방문 처리
            queue.append(i)         # 큐에 추가
            answer += 1             # 감염된 컴퓨터 수 증가

# DFS
# 각 컴퓨터를 최대 한 번만 방문하므로 O(N + T)
# def dfs(current):
#     global answer
#     visited[current] = True       # 현재 컴퓨터 방문 처리

#     for i in computers[current]:  # 현재 컴퓨터와 연결된 모든 컴퓨터 확인
#         if not visited[i]:        # 방문하지 않은 컴퓨터라면
#             answer += 1           # 감염된 컴퓨터 수 증가
#             dfs(i)                # 연결된 컴퓨터로 이동하여 재귀 호출

# dfs(1)                            # 1번 컴퓨터부터 시작
        
print(answer)