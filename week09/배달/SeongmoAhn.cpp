#include <iostream>
#include <algorithm>
#include <vector>
#include <queue>
#include <string>
using namespace std;
#define SIZE 51
#define INF 1e9

vector<pair<int, int>> graph[SIZE];
int graph_floyd[SIZE][SIZE];

void init_input(int &N, vector<vector<int>> &road) {
    for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= N; j++) {
            if (i != j) graph_floyd[i][j] = INF;
        }
    }

    for (int i = 0; i < road.size(); i++) {
        int node1 = road[i][0], node2 = road[i][1], weight = road[i][2];
        graph[node1].push_back({weight, node2});
        graph[node2].push_back({weight, node1});
        graph_floyd[node1][node2] = min(graph_floyd[node1][node2], weight);
        graph_floyd[node2][node1] = min(graph_floyd[node2][node1], weight);
    }
}

int dijkstra(int N, int K) {
    int distTable[SIZE]; fill(distTable, distTable + SIZE, INF);
    distTable[1] = 0;

    priority_queue<pair<int, int>> pq;
    pq.push({0, 1});
    while (!pq.empty()) {
        int curDist = -pq.top().first;
        int curNode = pq.top().second;
        pq.pop();

        for (int i = 0; i < graph[curNode].size(); i++) {
            int nextDist = graph[curNode][i].first;
            int nextNode = graph[curNode][i].second;

            int cost = curDist + nextDist;

            if (cost < distTable[nextNode]) {
                distTable[nextNode] = cost;
                pq.push({-cost, nextNode});
            }
        }
    }

    int sum = 0;
    for (int i = 1; i <= N; i++) {
        if (distTable[i] <= K)
            sum++;
    }
    return sum;
}

int floyd(int N, int K) {
    for (int k = 1; k <= N; k++) {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                graph_floyd[i][j] = min(graph_floyd[i][j], graph_floyd[i][k] + graph_floyd[k][j]);
            }
        }
    }

    int sum = 0;
    for (int i = 1; i <= N; i++) {
        if (graph_floyd[1][i] <= K)
            sum++;
    }
    return sum;
}

int solution(int N, vector<vector<int>> road, int K) {
    init_input(N, road);

    // int (*short_path)(int, int) = dijkstra;
    int (*short_path)(int, int) = floyd;
    return short_path(N, K);
}

int main(void) {
    int N = 6;
    vector<vector<int>> road = {{1, 2, 1}, {1, 3, 2}, {2, 3, 2}, {3, 4, 3}, {3, 5, 2}, {3, 5, 3}, {5, 6, 1}};
    int K = 4;

    cout << solution(N, road, K) << endl;
}
