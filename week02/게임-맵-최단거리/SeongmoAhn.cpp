#include <vector>
#include <queue>
using namespace std;

int solution(vector<vector<int> > maps) {
    int dy[] = {-1, 1, 0, 0}, dx[] = {0, 0, -1, 1};
    queue<pair<pair<int, int>, int>> q; // <<y, x>, cnt>
    int r = maps.size(), c = maps[0].size();
    
    q.push({{0, 0}, 1});
    maps[0][0] = 0;
    while (!q.empty()) {
        int y = q.front().first.first, x = q.front().first.second;
        int cnt = q.front().second;
        q.pop();
        if (y == r - 1 && x == c - 1) return cnt;
        
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            
            if (ny >= 0 && nx >= 0 && ny < r && nx < c && maps[ny][nx]) {
                q.push({{ny, nx}, cnt + 1});
                maps[ny][nx] = 0;
            }
        }
    }
    
    return -1;
}