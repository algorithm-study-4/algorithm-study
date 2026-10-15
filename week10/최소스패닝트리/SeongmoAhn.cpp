#include <iostream>
#include <algorithm>
#include <vector>
#include <tuple>
#include <queue>
using namespace std;
#define endl '\n'
#define SIZE 10001

int V, E;

// For Kruscal
int parent[SIZE];
vector<tuple<int, int, int>> graph_kruscal; // <가중치, 노드, 노드>

// For Prim
vector<pair<int, int>> graph[SIZE]; // <노드, 가중치>
priority_queue<pair<int, int>> pq; // <-가중치, 노드>
bool visited[SIZE];

int findParent(int v) {
    if (parent[v] == v) return v;
    return parent[v] = findParent(parent[v]);
}

bool unionParent(int v1, int v2) {
    int v = findParent(v1);
    int w = findParent(v2);

    if (v == w) return false;
    else {
        parent[w] = v;
        return true;
    }
}

int kruscal() {
    int ans = 0;
    for (int i = 0; i < E; i++) {
        if (unionParent(get<1>(graph_kruscal[i]), get<2>(graph_kruscal[i])))
            ans += get<0>(graph_kruscal[i]);
    }

    return ans;
}

int prim() {
    int ans = 0;

    pq.push({0, 1});

    while (!pq.empty()) {
        int curWeight = -pq.top().first;
        int curNode = pq.top().second;
        pq.pop();

        if (visited[curNode]) continue;

        visited[curNode] = true;
        ans += curWeight;

        for (int i = 0; i < graph[curNode].size(); i++) {
            int nextNode = graph[curNode][i].first;
            int nextWeight = graph[curNode][i].second;

            if (visited[nextNode]) continue;
            pq.push({-nextWeight, nextNode});
        }
    }

    return ans;
}

void init_input() {
    cin >> V >> E;
    for (int i = 1; i <= V; i++) parent[i] = i;
    for (int i = 0; i < E; i++) {
        int a, b, weight;
        cin >> a >> b >> weight;

        // For Kruscal
        graph_kruscal.push_back({weight, a, b});

        // For Prim
        graph[a].push_back({b, weight});
        graph[b].push_back({a, weight});
    }
}

void solve() {
    sort(graph_kruscal.begin(), graph_kruscal.end());;

    // int (*mst)() = kruscal;
    int (*mst)() = prim;
    cout << mst();
}

int main(void) {
    cout.tie(NULL); cin.tie(NULL); ios_base::sync_with_stdio(false);
    // freopen("input.txt", "r", stdin);
    init_input();
    solve();

    return 0;
}
