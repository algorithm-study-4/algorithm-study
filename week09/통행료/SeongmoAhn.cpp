#include <iostream>
#include <algorithm>
#include <vector>
#include <queue>
using namespace std;
#define endl '\n'
#define SIZE 1001
#define INF 1e9

int N, M, K, start, dest;
vector<pair<int, int>> graph[SIZE]; // <weight, linked_node>
vector<int> raise;
int ans[SIZE];

void init_input() {
    cin >> N >> M >> K >> start >> dest;
    for (int i = 0; i < M; i++) {
        int node1, node2, weight;
        cin >> node1 >> node2 >> weight;
        graph[node1].push_back({weight, node2});
        graph[node2].push_back({weight, node1});
    }

    raise.push_back(0);
    for (int i = 0; i < K; i++) {
        int n; cin >> n;
        raise.push_back(n);
    }
}

int dijkstra() {
    int distTable[SIZE]; fill(distTable, distTable + SIZE, INF);
    distTable[start] = 0;
    priority_queue<pair<int, int>> pq;
    pq.push({0, start});

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

    return distTable[dest];
}

void solve() {
    int (*short_path)() = dijkstra;

    for (int i = 0; i < raise.size(); i++) {
        int r = raise[i];
        for (int j = 0; j < SIZE; j++) {
            for (int k = 0; k < graph[j].size(); k++)
                graph[j][k].first += r;
        }
        cout << dijkstra() << endl;
    }
}

int main(void) {
    cout.tie(NULL); cin.tie(NULL); ios_base::sync_with_stdio(false);
    // freopen("input.txt", "r", stdin);
    init_input();
    solve();

    return 0;
}
