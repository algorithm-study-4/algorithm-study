#include <iostream>
#include <algorithm>
#include <vector>
#include <string>
using namespace std;

int ans;
bool visited[10];

void dfs(vector<vector<int>> &dungeons, int k, int cnt) {
    ans = max(ans, cnt);

    for (int i = 0; i < dungeons.size(); i++) {
        if (visited[i]) continue;

        int minTiredness = dungeons[i][0];
        int tiredness = dungeons[i][1];

        // 최소 필요 피로도 부족
        if (k < minTiredness) continue;

        visited[i] = true;
        dfs(dungeons, k - tiredness, cnt + 1);
        visited[i] = false;
    }
}

int solution(int k, vector<vector<int>> dungeons) {
    dfs(dungeons, k, 0);

    return ans;
}

int main(void) {
    int k = 80;
vector<vector<int>> dungeons = {{80, 20}, {50, 40}, {30, 10}};

cout << solution(k, dungeons) << endl;
}

