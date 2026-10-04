#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int k, n; // 현재 피로도, 던전 개수
vector<vector<int>> dungeons;
bool visited[8];
int max_cnt = 0;

void dfs(int fatigue, int cnt)
{
    max_cnt = max(cnt, max_cnt);

    for (int i = 0; i < dungeons.size(); i++)
    {
        if (visited[i])
            continue;

        if (fatigue < dungeons[i][0])
            continue;

        visited[i] = true;

        dfs(fatigue - dungeons[i][1], cnt + 1);

        visited[i] = false;
    }
}

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> k >> n;

    dungeons.resize(n, (vector<int>(2, 0)));
    for (int i = 0; i < n; i++)
        for (int j = 0; j < 2; j++)
            cin >> dungeons[i][j]; // ["최소 필요 피로도", "소모 피로도"]

    dfs(k, 0);

    cout << max_cnt << '\n';

    return 0;
}