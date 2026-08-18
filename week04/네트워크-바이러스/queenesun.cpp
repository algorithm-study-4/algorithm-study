#include <iostream>
#include <vector>
using namespace std;

vector<vector<int>> graph; // 연결 컴퓨터 쌍 담을 벡터
vector<bool> visited;      // 방문 여부 검사할 벡터
int cnt = 0;               // 감염되는 컴퓨터 수

void dfs(int node)
{
    visited[node] = true;

    for (int next : graph[node]) // 정점 N개, 간선 T개 확인 O(N + T)
        if (!visited[next])
        {
            cnt++;
            dfs(next);
        }
}

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int N; // 컴퓨터의 수 (번호는 1번 ~ N번)
    cin >> N;

    int T; // 직접 연결되어 있는 컴퓨터 쌍의 수
    cin >> T;

    graph.resize(N + 1, vector<int>(0)); // 컴퓨터의 번호가 1번 ~ N번이므로
    visited.resize(N + 1, false);

    for (int i = 0; i < T; i++) // T번 수행 O(T)
    {
        int a, b;
        cin >> a >> b;

        graph[a].push_back(b);
        graph[b].push_back(a);
    }

    dfs(1);

    cout << cnt << endl;

    return 0;
}

/*
- 연결된 것 전부 찾기 = DFS(Stack/재귀)
- 최단거리 구하기 = BFS(Queue)

이 문제의 경우 1번과 연결된 컴퓨터만 전부 방문하면 됨 -> dfs / bfs 둘 다 사용 가능
*/

#include <iostream>
#include <vector>
#include <queue>

using namespace std;

vector<vector<int>> graph;
vector<bool> visited;
int cnt = 0;

void bfs(int start)
{
    queue<int> q; // 먼저 들어온 애부터 처리해야 하므로 큐 사용

    q.push(start);
    visited[start] = true;

    while (!q.empty())
    {
        int cur = q.front();
        q.pop();

        for (int next : graph[cur])
        {
            if (!visited[next])
            {
                visited[next] = true;
                cnt++;

                q.push(next);
            }
        }
    }
}

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int N; // 컴퓨터의 수 (번호는 1번 ~ N번)
    cin >> N;

    int T; // 직접 연결되어 있는 컴퓨터 쌍의 수
    cin >> T;

    graph.resize(N + 1, vector<int>(0)); // 컴퓨터의 번호가 1번 ~ N번이므로
    visited.resize(N + 1, false);

    for (int i = 0; i < T; i++) // T번 수행 O(T)
    {
        int a, b;
        cin >> a >> b;

        graph[a].push_back(b);
        graph[b].push_back(a);
    }

    bfs(1);

    cout << cnt << endl;

    return 0;
}