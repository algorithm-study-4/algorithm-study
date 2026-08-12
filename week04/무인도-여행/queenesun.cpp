#include <string>
#include <vector>
#include <algorithm>

using namespace std;

vector<vector<bool>> visited;

int dx[4] = {1, -1, 0, 0};
int dy[4] = {0, 0, 1, -1};

int dfs(vector<string> &maps, int x, int y)
{
    visited[x][y] = true;

    int sum = maps[x][y] - '0';

    for (int i = 0; i < 4; i++)
    {
        int nx = x + dx[i];
        int ny = y + dy[i];

        // 맵 범위를 벗어난 경우
        if (nx < 0 || nx >= maps.size() || ny < 0 || ny >= maps[0].size())
            continue;

        // 방문했거나 바다인 경우
        if (visited[nx][ny] || maps[nx][ny] == 'X')
            continue;

        sum += dfs(maps, nx, ny); // 연결된 칸마다 또 dfs 호출
    }

    return sum;
}

vector<int> solution(vector<string> maps)
{
    visited.resize(maps.size(), vector<bool>(maps[0].size(), false));
    vector<int> result;

    // 전체 맵 돌면서 아직 방문하지 않은 섬이 있다면 DFS
    for (int i = 0; i < maps.size(); i++)
        for (int j = 0; j < maps[0].size(); j++)
        {
            if (maps[i][j] == 'X' || visited[i][j])
                continue;

            int sum = dfs(maps, i, j);
            result.push_back(sum);
        }

    if (result.empty())
        return {-1}; // solution 함수의 반환형이 vector<int>라서 vector<int>{-1} 반환해줘야 함
    else
    {
        sort(result.begin(), result.end());
        return result;
    }
}

// maps 크기가 N * M 이라고 가정하면 DFS 부분은 O(N * M)
// result 크기가 K 라고 가정하면 sort 부분 O(K log K)
// O(NM + K log K)