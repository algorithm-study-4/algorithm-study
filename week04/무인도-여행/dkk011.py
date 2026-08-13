# 맵 크기가 N * M, result 길이가 K라면
# O(N * M) + O(K log K)
from collections import deque

def solution(maps):
    # 상하좌우 이동
    dx = [0, 0, -1, 1]
    dy = [-1, 1, 0, 0]
    
    # 문자열 형태의 maps를 각 문자를 원소로 갖는 2차원 리스트로 변환
    # ["123", "4X5"] -> [["1", "2", "3"], ["4", "X", "5"]]
    maps = [list(i) for i in maps]
    
    def bfs(x, y):
        queue = deque([(x, y)])     # 큐에 시작 위치 추가
        stay = int(maps[x][y])      # 현재 칸의 숫자 합
        maps[x][y] = "X"            # "X"로 바꿔 방문 처리
        
        while queue:
            x, y = queue.popleft()  # 큐에서 현재 위치 꺼내기
            
            # 상하좌우 확인
            for i in range(4):
                nx, ny = x + dx[i], y + dy[i]
                
                # 이동하는 위치가 범위 내에 있고, 아직 방문하지 않은 곳인 경우
                if 0 <= nx < len(maps) and 0 <= ny < len(maps[0]) and maps[nx][ny] != "X":
                    stay += int(maps[nx][ny])   # 해당 칸의 숫자를 더함
                    maps[nx][ny] = "X"          # 방문 처리
                    queue.append((nx, ny))      # 다음 탐색 위치로 추가

        return stay
    
    # 모든 칸을 순회하면서 아직 방문하지 않은 칸을 발견하면 BFS 수행
    result = [bfs(i, j) for i in range(len(maps)) for j in range(len(maps[0])) if maps[i][j] != "X"]
    
    # 오름차순 정렬, result가 비어 있으면 [-1]
    return sorted(result) or [-1]