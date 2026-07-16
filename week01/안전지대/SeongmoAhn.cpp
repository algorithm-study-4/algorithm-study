#include <string>
#include <vector>

using namespace std;

int solution(vector<vector<int>> board) {
    int answer = 0;
    int dy[] = {-1, 1, 0, 0, -1, -1, 1, 1};
    int dx[] = {0, 0, -1, 1, 1, -1, 1, -1};
    int visited[101][101] = {0};
    
    for (int i = 0; i < board.size(); i++) {
        for (int j = 0; j < board.size(); j++) {
            if (board[i][j] == 1) {
               	visited[i][j] = 1; 
                
                for (int k = 0; k < 8; k++) {
                    int ny = i + dy[k];
                    int nx = j + dx[k];
                    
                    if (ny < 0 || nx < 0 || ny >= board.size() || nx >= board.size()) continue;
                    visited[ny][nx] = 1;
                }
            }
        }
    }
    
    for (int i = 0; i < board.size(); i++) {
        for (int j = 0; j < board.size(); j++) {
            if (!visited[i][j]) answer++;
        }
    }
    
    return answer;
}