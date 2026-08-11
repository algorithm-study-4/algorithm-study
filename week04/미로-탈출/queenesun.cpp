#include <string>
#include <vector>
#include <queue>

using namespace std;

int dx[4] = {1, -1, 0, 0};
int dy[4] = {0, 0, 1, -1};

// 이동 경로 S -> L -> E (BFS 두 번으로 쪼개서 최단 거리 찾기)
int bfs(vector<string> &maps,
        int startX, int startY,
        int targetX, int targetY)
{
    // 거리 배열 만들기 (-1 = 아직 방문하지 않음)
    vector<vector<int>> dist(maps.size(), vector<int>(maps[0].size(), -1));

    // 큐 만들기
    queue<pair<int, int>> q;

    // 시작점
    q.push({startX, startY});
    dist[startX][startY] = 0;

    while (!q.empty())
    {
        auto [x, y] = q.front();
        q.pop();

        // 타겟에 도착했다면 거리 반환
        if (x == targetX && y == targetY)
            return dist[x][y];

        // 상하좌우
        for (int i = 0; i < 4; i++)
        {
            int nx = x + dx[i];
            int ny = y + dy[i];

            // 맵 밖
            if (nx < 0 || nx >= maps.size() ||
                ny < 0 || ny >= maps[0].size())
                continue;

            // 벽
            if (maps[nx][ny] == 'X')
                continue;

            // 이미 방문
            if (dist[nx][ny] != -1)
                continue;

            // 거리 기록
            dist[nx][ny] = dist[x][y] + 1;

            // 큐에 넣기
            q.push({nx, ny});
        }
    }

    // 타겟에 도달할 수 없음
    return -1;
}

int solution(vector<string> maps)
{
    int sx, sy;
    int lx, ly;
    int ex, ey;

    // S, L, E 위치 찾기
    for (int i = 0; i < maps.size(); i++)
    {
        for (int j = 0; j < maps[0].size(); j++)
        {
            if (maps[i][j] == 'S')
            {
                sx = i;
                sy = j;
            }
            else if (maps[i][j] == 'L')
            {
                lx = i;
                ly = j;
            }
            else if (maps[i][j] == 'E')
            {
                ex = i;
                ey = j;
            }
        }
    }

    // S → L
    int first = bfs(maps, sx, sy, lx, ly);

    if (first == -1)
        return -1;

    // L → E
    int second = bfs(maps, lx, ly, ex, ey);

    if (second == -1)
        return -1;

    return first + second;
}