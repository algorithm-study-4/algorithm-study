# N = rows, M = cols
# S, L, E 찾을 때 O(N * M)
# BFS로 S에서 L까지 최단 시간 계산 O(N * M)
# BFS로 L에서 E까지 최단 시간 계산 O(N * M)
# O(N * M)

from collections import deque

def solution(maps):
    # 상하좌우 이동
    dx = [-1, 1, 0, 0]
    dy = [0, 0, -1, 1]

    rows = len(maps)
    cols = len(maps[0])

    # 시작 지점, 레버, 출구 위치 찾기
    for i in range(rows):
        for j in range(cols):
            if maps[i][j] == 'S':
                start = (i, j)
            elif maps[i][j] == 'L':
                lever = (i, j)
            elif maps[i][j] == 'E':
                end = (i, j)

    # BFS로 start에서 target까지 최단 시간 계산
    def bfs(start, target):
        queue = deque([start])

        # 각 위치까지의 이동 시간 저장, 아직 방문하지 않은 곳은 -1
        dist = [[-1] * cols for _ in range(rows)]

        # 시작 위치 시간은 0
        dist[start[0]][start[1]] = 0

        while queue:
            x, y = queue.popleft()

            if (x, y) == target:    # 현재 위치가 target이면 최소 시간 반환
                return dist[x][y]

            for d in range(4):      # 상하좌우 확인
                nx = x + dx[d]
                ny = y + dy[d]

                # 범위를 벗어나면 이동하지 않음
                if not (0 <= nx < rows and 0 <= ny < cols):
                    continue

                # 벽이면 이동하지 않음
                if maps[nx][ny] == 'X':
                    continue

                # 이미 방문한 곳이면 이동하지 않음
                if dist[nx][ny] != -1:
                    continue

                # 현재 위치에서 한 칸(1초) 이동한 시간을 저장하고 큐에 추가
                dist[nx][ny] = dist[x][y] + 1
                queue.append((nx, ny))

        # target에 도달하지 못하면 -1 반환
        return -1

    dist1 = bfs(start, lever)

    if dist1 == -1:
        return -1

    dist2 = bfs(lever, end)

    if dist2 == -1:
        return -1

    # 두 구간의 최소 시간 합 반환
    return dist1 + dist2