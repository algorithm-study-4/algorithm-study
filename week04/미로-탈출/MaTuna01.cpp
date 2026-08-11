#include <string>
#include <vector>
#include <queue>

using namespace std;

// 큐에 담을 좌표와 거리 정보를 구조체로 정의
struct Node {
    int r, c, cost;
};

// 시작 지점(start)에서 목표 지점(end)까지의 최단 거리를 구하는 BFS 함수
int bfs(const vector<string>& maps, char start, char end) {
    int n = maps.size();
    int m = maps[0].size();
    
    vector<vector<bool>> visited(n, vector<bool>(m, false));
    queue<Node> q;
    
    // 1. 시작점 찾기 및 큐에 삽입
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < m; ++j) {
            if (maps[i][j] == start) {
                q.push({i, j, 0});
                visited[i][j] = true;
                break;
            }
        }
    }
    
    // 상하좌우 이동 방향
    int dr[] = {-1, 1, 0, 0};
    int dc[] = {0, 0, -1, 1};
    
    // 2. BFS 탐색
    while (!q.empty()) {
        Node cur = q.front();
        q.pop();
        
        // 목적지에 도달한 경우 현재까지의 거리 반환
        if (maps[cur.r][cur.c] == end) {
            return cur.cost;
        }
        
        for (int i = 0; i < 4; ++i) {
            int nr = cur.r + dr[i];
            int nc = cur.c + dc[i];
            
            // 맵의 범위를 벗어나지 않고, 방문하지 않았으며, 벽('X')이 아닌 경우
            if (nr >= 0 && nr < n && nc >= 0 && nc < m) {
                if (!visited[nr][nc] && maps[nr][nc] != 'X') {
                    visited[nr][nc] = true;
                    q.push({nr, nc, cur.cost + 1});
                }
            }
        }
    }
    
    return -1; // 목적지에 도달할 수 없는 경우
}

int solution(vector<string> maps) {
    // 시작점(S)에서 레버(L)까지의 최단 거리
    int path1 = bfs(maps, 'S', 'L');
    // 레버(L)에서 출구(E)까지의 최단 거리
    int path2 = bfs(maps, 'L', 'E');
    
    // 두 경로 모두 이동 가능한 경우 더해서 반환
    if (path1 != -1 && path2 != -1) {
        return path1 + path2;
    }
    
    return -1;
}

// DFS
// const int INF = 987654321; //무한대
// int dr[] = {-1, 1, 0, 0};
// int dc[] = {0, 0, -1, 1};

// // DFS 재귀 함수
// void dfs(const vector<string>& maps, vector<vector<int>>& cost_map, int r, int c, int current_cost, char end) {
//     int n = maps.size();
//     int m = maps[0].size();
    
//     for (int i = 0; i < 4; ++i) {
//         int nr = r + dr[i];
//         int nc = c + dc[i];
        
//         // 맵의 범위를 벗어나지 않고 벽이 아닌 경우
//         if (nr >= 0 && nr < n && nc >= 0 && nc < m && maps[nr][nc] != 'X') {
//             // 새로 이동하는 경로가 기존에 기록된 최단 거리보다 짧은 경우에만 탐색 진행 (가지치기)
//             if (current_cost + 1 < cost_map[nr][nc]) {
//                 cost_map[nr][nc] = current_cost + 1;
                
//                 // 도착지가 아니라면 계속 더 깊이 탐색
//                 if (maps[nr][nc] != end) {
//                     dfs(maps, cost_map, nr, nc, current_cost + 1, end);
//                 }
//             }
//         }
//     }
// }

// 특정 지점 간의 최소 비용을 DFS로 계산하는 헬퍼 함수
// int dfs_shortest(const vector<string>& maps, char start, char end) {
//     int n = maps.size();
//     int m = maps[0].size();
    
//     // 도달 비용을 INF로 초기화
//     vector<vector<int>> cost_map(n, vector<int>(m, INF));
    
//     int sr = -1, sc = -1;
    
//     // 시작점 찾기
//     for (int i = 0; i < n; ++i) {
//         for (int j = 0; j < m; ++j) {
//             if (maps[i][j] == start) {
//                 sr = i;
//                 sc = j;
//                 break;
//             }
//         }
//     }
    
//     // 시작 위치의 비용을 0으로 설정하고 DFS 시작
//     cost_map[sr][sc] = 0;
//     dfs(maps, cost_map, sr, sc, 0, end);
    
//     // 목적지 좌표를 찾아 최소 비용 반환
//     for (int i = 0; i < n; ++i) {
//         for (int j = 0; j < m; ++j) {
//             if (maps[i][j] == end) {
//                 return cost_map[i][j] == INF ? -1 : cost_map[i][j];
//             }
//         }
//     }
    
//     return -1;
// }

// int solution(vector<string> maps) {
//     int path1 = dfs_shortest(maps, 'S', 'L');
//     int path2 = dfs_shortest(maps, 'L', 'E');
    
//     if (path1 != -1 && path2 != -1) {
//         return path1 + path2;
//     }
    
//     return -1;
// }