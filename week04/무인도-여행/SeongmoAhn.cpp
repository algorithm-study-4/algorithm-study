#include <string>
#include <vector>
#include <queue>
#include <algorithm>
#include <iostream>

using namespace std;

int dy[] = {-1, 1, 0, 0}, dx[] = {0, 0, -1, 1};
int visited[101][101];
int row, col;

int canMove(int y, int x) {
    return y >= 0 && x >= 0 && y < row && x < col && !visited[y][x];
}

int dfs(vector<string> &maps, int y, int x) {
    visited[y][x] = 1;
   	int cnt = maps[y][x] - '0'; 
    
    for (int i = 0; i < 4; i++) {
        int ny = y + dy[i];
        int nx = x + dx[i];
       	
		if (canMove(ny, nx) && maps[ny][nx] != 'X') 
            cnt += dfs(maps, ny, nx);
    }
    
    return cnt;
}
    
int bfs(vector<string> &maps, int startY, int startX) {
    queue<pair<int, int>> q;
	int cnt = 0;
    
    visited[startY][startX] = 1;
	q.push({startY, startX});
                
	while (!q.empty()) {
		auto [curY, curX] = q.front(); q.pop();
		cnt += maps[curY][curX] - '0';
		            
		for (int i = 0; i < 4; i++) {
			int ny = curY + dy[i];
			int nx = curX + dx[i];
		                
			if (canMove(ny, nx) && maps[ny][nx] != 'X') {
				visited[ny][nx] = 1;
				q.push({ny, nx});
            }
		}
	}
    return cnt;
}

vector<int> solution(vector<string> maps) {
    row = maps.size();
    col = maps[0].size();
    vector<int> answer;
    
    for (int i = 0; i < row; i++) {
        for (int j = 0; j < col; j++) {
            if (maps[i][j] != 'X' && !visited[i][j]) {
           		answer.push_back(bfs(maps, i, j)); 
           		//answer.push_back(dfs(maps, i, j)); 
            }
        }
    }
    sort(answer.begin(), answer.end());
    
    if (!answer.size()) answer.push_back(-1);
    return answer;
}