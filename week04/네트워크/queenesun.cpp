#include <string>
#include <vector>

using namespace std;

vector<bool> visited;

void dfs(vector<vector<int>> &computers, int node) // node = 현재 컴퓨터
{
    visited[node] = true;

    for (int next = 0; next < computers.size(); next++)
        if (computers[node][next] == 1 && !visited[next])
            dfs(computers, next);
}

int solution(int n, vector<vector<int>> computers)
{
    visited.resize(n, false);
    int answer = 0;

    for (int i = 0; i < n; i++) // 모든 컴퓨터에 대해 확인
        if (!visited[i])
        {
            dfs(computers, i);
            answer++;
        }

    return answer;
}

// 컴퓨터 한 대당 next를 0 ~ n-1 까지 전부 확인(n번)
// 컴퓨터의 개수가 n이므로 O(n^2)