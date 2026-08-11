#include<string>
#include<vector>
#include<algorithm>
#include<queue>
#include<iostream>

using namespace std;
//BFS
vector<int> solution(vector<string> maps) {
    int rows = maps.size();
    int cols = maps[0].size();
    
    // 방문 여부 체크 배열
    vector<vector<bool>> visited(rows, vector<bool>(cols, false));
    vector<int> answer;
    
    // 상하좌우 이동 방향
    int dr[] = {-1, 1, 0, 0};
    int dc[] = {0, 0, -1, 1};
    
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            // 바다가 아니고 방문하지 않은 땅을 만나면 BFS 시작
            if (maps[i][j] != 'X' && !visited[i][j]) {
                int total_food = 0;
                queue<pair<int, int>> q;
                
                q.push({i, j});
                visited[i][j] = true;
                total_food += maps[i][j] - '0'; // 문자를 숫자로 변환하여 누적
                
                while (!q.empty()) {
                    int r = q.front().first;
                    int c = q.front().second;
                    q.pop();
                    
                    for (int d = 0; d < 4; d++) {
                        int nr = r + dr[d];
                        int nc = c + dc[d];
                        
                        // 지도를 벗어나지 않으며, 방문하지 않았고, 바다가 아닌 경우
                        if (nr >= 0 && nr < rows && nc >= 0 && nc < cols) {
                            if (maps[nr][nc] != 'X' && !visited[nr][nc]) {
                                visited[nr][nc] = true;
                                total_food += maps[nr][nc] - '0';
                                q.push({nr, nc});
                            }
                        }
                    }
                }
                answer.push_back(total_food);
            }
        }
    }
    
    // 섬이 없으면 -1 반환, 있으면 오름차순 정렬
    if (answer.empty()) return {-1};
    
    sort(answer.begin(), answer.end());
    return answer;
}
// DFS
// vector<int> solution(vector<string> maps) {
//     int rows = maps.size();
//     int cols = maps[0].size();
    
//     vector<vector<bool>> visited(rows, vector<bool>(cols, false));
//     vector<int> answer;
    
//     int dr[] = {-1, 1, 0, 0};
//     int dc[] = {0, 0, -1, 1};
    
//     // 재귀 람다 함수 정의
//     auto dfs = [&](auto& self, int r, int c) -> int {
//         visited[r][c] = true;
//         int total_food = maps[r][c] - '0';
        
//         for (int i = 0; i < 4; i++) {
//             int nr = r + dr[i];
//             int nc = c + dc[i];
            
//             // 지도를 벗어나지 않으며, 방문하지 않았고, 바다가 아닌 경우 재귀 호출
//             if (nr >= 0 && nr < rows && nc >= 0 && nc < cols) {
//                 if (maps[nr][nc] != 'X' && !visited[nr][nc]) {
//                     total_food += self(self, nr, nc); // 반환된 식량을 누적
//                 }
//             }
//         }
//         return total_food;
//     };
    
//     for (int i = 0; i < rows; i++) {
//         for (int j = 0; j < cols; j++) {
//             if (maps[i][j] != 'X' && !visited[i][j]) {
//                 answer.push_back(dfs(dfs, i, j));
//             }
//         }
//     }
    
//     if (answer.empty()) return {-1};
    
//     sort(answer.begin(), answer.end());
//     return answer;
// }