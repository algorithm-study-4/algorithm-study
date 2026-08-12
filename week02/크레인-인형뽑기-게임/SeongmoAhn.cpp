#include <string>
#include <algorithm>
#include <vector>

using namespace std;

int solution(vector<vector<int>> board, vector<int> moves) {
    int answer = 0;
    vector<vector<int>> ss(31);
    vector<int> s;
    for (int i = 0; i < board.size(); i++) {
        for (int j = 0; j < board.size(); j++) {
            if (board[j][i]) ss[i].push_back(board[j][i]);
        }
        reverse(ss[i].begin(), ss[i].end());
    }
    for (auto idx : moves) {
        if (ss[idx - 1].empty()) continue;
        int cur = ss[idx - 1].back();
        ss[idx - 1].pop_back();
        
        if (s.empty()) {
            s.push_back(cur);
        }
        else {
            if (s.back() == cur) {
                answer += 2;
                s.pop_back();
            }
            else {
                s.push_back(cur);
            }
        }
    }
    return answer;
}