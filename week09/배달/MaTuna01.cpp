#include <iostream>
#include <vector>
#include <queue>

using namespace std;

const int INF = 1e9;

int solution(int N, vector<vector<int>> road, int K) {
    vector<vector<pair<int, int>>> graph(N + 1);
    for (const auto& r : road) {
        int u = r[0];
        int v = r[1];
        int w = r[2];
        graph[u].push_back({v, w});
        graph[v].push_back({u, w}); // 양방향 도로
    }

    vector<int> dist(N + 1, INF);

    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;

    dist[1] = 0;
    pq.push({0, 1});

    while (!pq.empty()) {
        auto [current_dist, u] = pq.top();
        pq.pop();

        if (current_dist > dist[u]) continue;

        for (const auto& edge : graph[u]) {
            int v = edge.first;
            int weight = edge.second;

            if (dist[u] + weight < dist[v]) {
                dist[v] = dist[u] + weight;
                pq.push({dist[v], v});
            }
        }
    }

    int answer = 0;
    for (int i = 1; i <= N; ++i) {
        if (dist[i] <= K) {
            answer++;
        }
    }

    return answer;
}