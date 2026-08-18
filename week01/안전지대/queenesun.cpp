#include <string>
#include <vector>

using namespace std;

// https://school.programmers.co.kr/learn/courses/30/lessons/120866

int solution(vector<vector<int>> board)
{
    int answer = 0;
    int n = board[0].size();

    int dx[8] = {-1, -1, -1, 0, 0, 1, 1, 1};
    int dy[8] = {-1, 0, 1, -1, 1, -1, 0, 1};

    for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++)
            if (board[i][j] == 1)
            {
                for (int k = 0; k < 8; k++)
                {
                    int nx = i + dx[k];
                    int ny = j + dy[k];

                    if (nx >= 0 && nx < n && ny >= 0 && ny < n && board[nx][ny] == 0)
                        board[nx][ny] = 2;
                }
            }

    for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++)
            if (board[i][j] == 0)
                answer++;

    return answer;
}