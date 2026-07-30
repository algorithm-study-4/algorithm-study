#include <string>
#include <vector>

using namespace std;

int solution(vector<vector<int>> board, vector<int> moves)
{
    int answer = 0;
    vector<int> stack(0);

    for (int i = 0; i < moves.size(); i++)
    {
        int where = moves[i] - 1; // 배열에서 위치를 찾아야 하므로 -1
        for (int j = 0; j < board.size(); j++)
        {
            if (board[j][where] != 0)
            {
                stack.push_back(board[j][where]);
                board[j][where] = 0;
                if (stack.size() >= 2 && stack[stack.size() - 1] == stack[stack.size() - 2])
                {
                    stack.pop_back();
                    stack.pop_back();

                    answer += 2;
                }

                break;
            }
            else
                continue;
        }
    }

    return answer;
}