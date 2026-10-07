#include <iostream>
#include <algorithm>
#include <vector>
#include <queue>
using namespace std;
#define endl '\n'
#define SIZE 401
#define INF 1e9

int N, M;
vector<int> graph[SIZE];
int graph_floyd[SIZE][SIZE];

void init_input() {
    cin >> N >> M;

    for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= N; j++) {
            if (i != j) graph_floyd[i][j] = INF;
        }
    }

    for (int i = 0; i < M; i++) {
        int a, b; cin >> a >> b;
        graph[a].push_back(b);
        graph[b].push_back(a);
        graph_floyd[a][b] = graph_floyd[b][a] = 1;
    }
}

int dijkstra(int start) {
    int distTable[SIZE]; // start로부터 각 노드까지 최단 거리를 저장하는 배열
    fill(distTable, distTable + SIZE, INF); // 큰 값으로 배열 꽉 채움
    distTable[start] = 0;

    queue<int> q; // 가중치가 없어서 일반 큐로 사용
    q.push(start);

    while (!q.empty()) {
        auto cur = q.front();
        q.pop();

        for (int i = 0; i < graph[cur].size(); i++) {
            int nextNode = graph[cur][i];

            int cost = distTable[cur] + 1;
            if (cost < distTable[nextNode]) {
                distTable[nextNode] = cost;
                q.push(nextNode);
            }
        }
    }

    int sum = 0; // 다른 모든 사람과의 거리의 합
    for (int i = 1; i <= N; i++) {
        sum += distTable[i];
    }
    return sum;
}

void floyd() {
    for (int k = 1; k <= N; k++) {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                // 출발지에서 도착지까지 바로 가는 경로와 k번 노드를 거쳐가는 경로를 비교해서 짧은 거리로 갱신
                graph_floyd[i][j] = min(graph_floyd[i][j], graph_floyd[i][k] + graph_floyd[k][j]);
            }
        }
    }
}

void solve(int type) {
    int ans = 0;
    int minSum = INF;

    if (type == 1) { // 다익스트라 방식
        for (int i = 1; i <= N; i++) {
            int sum = dijkstra(i);
            if (sum < minSum) { // 동점이면 번호가 작은 사람 유지
                minSum = sum;
                ans = i;
            }
        }
    } else { // 플로이드-워셜 방식
        floyd();

        for (int i = 1; i <= N; i++) {
            int sum = 0;
            for (int j = 1; j <= N; j++) {
                if (i == j) continue;
                sum += graph_floyd[i][j];
            }

            if (sum < minSum) { // 동점이면 번호가 작은 사람 유지
                minSum = sum;
                ans = i;
            }
        }
    }

    cout << ans;
}

int main(void) {
    cout.tie(NULL); cin.tie(NULL); ios_base::sync_with_stdio(false);
    // freopen("input.txt", "r", stdin);
    init_input();
    solve(2);

    return 0;
}
