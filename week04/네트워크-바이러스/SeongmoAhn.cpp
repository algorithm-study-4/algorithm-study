#include <iostream>
#include <vector>
#include <queue>
using namespace std;
#define endl '\n'
#define SIZE 101

int N, T;
vector<int> graph[SIZE];
int visited[SIZE];

void init_input() {
    cin >> N >> T;
    while (T--) {
        int a, b; cin >> a >> b;
        graph[a].push_back(b);
        graph[b].push_back(a);
    }
}

void bfs() {
    queue<int> q;
    q.push(1);
    visited[1] = 1;

    int cnt = 0;
    while (!q.empty()) {
        int cur = q.front();
        q.pop();

        for (int i = 0; i < graph[cur].size(); i++) {
            int next = graph[cur][i];
            if (!visited[next]) {
                q.push(next);
                visited[next] = 1;
                cnt++;
            }
        }
    }
    cout << cnt;
}

int dfs(int cur) {
    int count = 1;
    for (int i = 0; i < graph[cur].size(); i++) {
        int next = graph[cur][i];
        if (!visited[next]) {
            visited[next] = 1;
            count += dfs(next);
        }
    }
    return count;
}

int main(void) {
    cout.tie(NULL); cin.tie(NULL); ios_base::sync_with_stdio(false);
    // freopen("input.txt", "r", stdin);
    init_input();
    bfs();
    // visited[1] = 1;
    // cout << dfs(1) - 1;

    return 0;
}
