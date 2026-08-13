#include <string>
#include <vector>
#include <cstring>
#include <algorithm>
#include <queue>
#define SIZE 101
using namespace std;

int row, col;
pair<int, int> S, E, L;
int visited[SIZE][SIZE] = {0};
int dy[] = {-1, 1, 0, 0};
int dx[] = {0, 0, -1, 1};

int canMove(vector<string> &maps, int y, int x) {
    return y >= 0 && x >= 0 && y < row && x < col && maps[y][x] != 'X';
}

int bfs(vector<string> &maps, pair<int, int> start, pair<int, int> end) {
	int visited[SIZE][SIZE] = {0};
    queue<pair<pair<int, int>, int>> q; // <<y, x> cnt>
    
    visited[start.first][start.second] = 1;
    q.push({start, 0});
    
    while (!q.empty()) {
        auto [cur, cnt] = q.front(); q.pop();
        auto [y, x] = cur;
        
        if (y == end.first && x == end.second)
            return cnt;
        
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            
            if (canMove(maps, ny, nx) && !visited[ny][nx]) {
                visited[ny][nx] = 1;
                q.push({{ny, nx}, cnt + 1});
            }
        }
    }
    
    return -1;
}

// 일부 테스트에서 시간초과
int dfs(vector<string> &maps, pair<int, int> start, pair<int, int> end, int cnt) {
    if (start == end)
        return cnt;
    
    int result = -1;
    for (int i = 0; i < 4; i++) {
        int ny = start.first + dy[i];
        int nx = start.second + dx[i];
            
        if (canMove(maps, ny, nx) && !visited[ny][nx]) {
            visited[ny][nx] = 1;
            int ret = dfs(maps, {ny, nx}, end, cnt + 1);
            visited[ny][nx] = 0;
            
            if (ret != -1)
                result = (result == -1) ? ret : min(result, ret);
        }
    }
    return result;
}

int solution(vector<string> maps) {
    row = maps.size();
    col = maps[0].size();
    
    for (int i = 0; i < row; i++) {
        for (int j = 0; j < col; j++) {
            if (maps[i][j] == 'S') S = {i, j};
            else if (maps[i][j] == 'E') E = {i, j};
            else if (maps[i][j] == 'L') L = {i, j};
        }
    }
    
    int cnt1 = bfs(maps, S, L);
    int cnt2 = bfs(maps, L, E);
    /*
    memset(visited, 0, sizeof(visited));
    visited[S.first][S.second] = 1;
    int cnt1 = dfs(maps, S, L, 0);
    memset(visited, 0, sizeof(visited));
    visited[L.first][L.second] = 1;
    int cnt2 = dfs(maps, L, E, 0);
    */
    
    return cnt1 < 0 || cnt2 < 0 ? -1 : cnt1 + cnt2;
}