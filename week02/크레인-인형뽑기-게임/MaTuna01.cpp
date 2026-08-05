#include <string>
#include <vector>
#include <stack>

using namespace std;

int solution(vector<vector<int>> board, vector<int> moves) {
    int answer = 0;
    stack<int> basket;

    for (int m : moves) {
        int col = m - 1;
        for (int row = 0; row < board.size(); row++) {
            if (board[row][col] != 0) {
                int doll = board[row][col];
                board[row][col] = 0;

                if (!basket.empty() && basket.top() == doll) {
                    basket.pop();
                    answer += 2;
                } else {
                    basket.push(doll);
                }
                break;
            }
        }
    }

    return answer;
}