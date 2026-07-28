from collections import deque

def solution(maps):

    row = len(maps)
    col = len(maps[0])
    
    # 상, 하, 좌, 우
    dy = [-1, 1, 0, 0]
    dx = [0, 0, -1, 1]
    
    # 큐 초기화: (y, x, 이동 거리)
    queue = deque([(0, 0, 1)])
    
    # 방문 처리 배열 (C++의 visited[SIZE][SIZE]와 동일)
    visited = [[False] * col for _ in range(row)]
    visited[0][0] = True
    
    while queue:
        cur_y, cur_x, cur_count = queue.popleft()
        
        # 디버깅 출력 (필요 시 주석 해제)
        # print(f"[{cur_y}, {cur_x}] 이동칸: {cur_count}")
        
        # 목적지에 도착한 경우 바로 반환
        if cur_y == row - 1 and cur_x == col - 1:
            return cur_count
        
        # 상하좌우 확인하기
        for i in range(4):
            next_y = cur_y + dy[i]
            next_x = cur_x + dx[i]
            
            # 맵 범위 체크
            if 0 <= next_y < row and 0 <= next_x < col:
            
            # 그래프 벗어났는지, 방문했는지, 벽 아닌지 체크
            
                # 벽이 아니고(1) 방문하지 않은 곳인 경우
                if maps[next_y][next_x] == 1 and not visited[next_y][next_x]:
                    visited[next_y][next_x] = True
                    
                    # 큐에 값 넣기
                    queue.append((next_y, next_x, cur_count + 1))
                    
    # 목적지에 도달하지 못한 경우
    return -1