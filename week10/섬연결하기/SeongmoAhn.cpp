#include <iostream>
#include <algorithm>
#include <vector>
#include <queue>
using namespace std;
#define SIZE 110

int parent[SIZE];

int findParent(int v) {
    if (parent[v] == v) return v;
    return parent[v] = findParent(parent[v]);
}

bool unionParent(int v1, int v2) {
    int v = findParent(v1);
    int w = findParent(v2);

    if (v == w) return false;

    parent[w] = v;
    return true;
}

int kruscal(int &n, vector<vector<int>> &costs) {
    for (int i = 0; i < n; i++) parent[i] = i;
    sort(costs.begin(), costs.end(), [](const vector<int>& a, const vector<int>& b) {
        return a[2] < b[2];
    });

    int ans = 0;
    for (int i = 0; i < costs.size(); i++) {
        if (unionParent(costs[i][0], costs[i][1]))
            ans += costs[i][2];
    }

    return ans;
}

int prim(int &n, vector<vector<int>> &costs) {
    vector<pair<int, int>> graph[SIZE];
    for (auto &c : costs) {
        graph[c[0]].push_back({c[1], c[2]});
        graph[c[1]].push_back({c[0], c[2]});
    }

    int ans = 0;
    bool visited[SIZE] = {0};
    priority_queue<pair<int, int>> pq; // <-가중치, 노드>
    pq.push({0, 0});
    while (!pq.empty()) {
        int curWeight = -pq.top().first;
        int curNode = pq.top().second;
        pq.pop();

        if (visited[curNode]) continue;
        visited[curNode] = true;
        ans += curWeight;

        for (auto &[nextNode, nextWeight] : graph[curNode]) {
            if (!visited[nextNode]) pq.push({-nextWeight, nextNode});
        }
    }

    return ans;
}

int solution(int n, vector<vector<int>> costs) {
    // int (*mst)(int &, vector<vector<int>> &) = kruscal;
    int (*mst)(int &, vector<vector<int>> &) = prim;

    return mst(n, costs);
}

int main(void) {
    cout << solution(4, {{0, 1, 1}, {0, 2, 2}, {1, 2, 5}, {1, 3, 1}, {2, 3, 8}}) << endl;
}
